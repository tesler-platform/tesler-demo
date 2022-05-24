import { of as observableOf, from as observableFrom, EMPTY } from 'rxjs'
import { mergeMap, filter, catchError, switchMap } from 'rxjs/operators'
import { CustomEpic, actionTypes } from '../interfaces/actions'
import { getBasicAuthRequest } from '../api/session'
import { LoginResponse } from '@tesler-ui/core/interfaces/session'
import { $do, SSO_AUTH } from '../actions/types'
import { AxiosError } from 'axios'
import { ofType } from 'redux-observable'
import { keycloak, keycloakOptions } from '../keycloak'

const responseStatusMessages: Record<number, string> = {
    401: 'Unauthorized',
    403: 'Access denied'
}

const ssoAuthEpic: CustomEpic = action$ =>
    action$.pipe(
        ofType(SSO_AUTH),
        switchMap(() => {
            return observableFrom(keycloak.init(keycloakOptions)).pipe(
                switchMap(() => observableOf($do.login({ login: '', password: '' }))),
                catchError(() => {
                    console.error('Authentication failed')
                    return EMPTY
                })
            )
        })
    )

const loginEpic: CustomEpic = action$ =>
    action$.pipe(
        ofType(actionTypes.login),
        filter(action => !action.payload?.role),
        switchMap(action => {
            const login = action.payload && action.payload.login
            const password = action.payload && action.payload.password
            return getBasicAuthRequest(login, password).pipe(
                mergeMap((data: LoginResponse) => {
                    return observableOf(
                        $do.loginDone({
                            devPanelEnabled: data.devPanelEnabled,
                            activeRole: data.activeRole,
                            roles: data.roles,
                            firstName: data.firstName,
                            lastName: data.lastName,
                            login: data.login,
                            screens: data.screens
                        })
                    )
                }),
                catchError((error: AxiosError) => {
                    const errorMsg = error.response
                        ? responseStatusMessages[error.response.status] || 'Server application unavailable'
                        : 'Empty response from server'
                    return observableOf($do.loginFail({ errorMsg }))
                })
            )
        })
    )

const logoutEpic: CustomEpic = action$ =>
    action$.pipe(
        ofType(actionTypes.logout),
        switchMap(() => {
            keycloak.logout()
            return observableOf($do.logoutDone(null))
        })
    )

const logoutDone: CustomEpic = action$ =>
    action$.pipe(
        ofType(actionTypes.logoutDone),
        switchMap(() => {
            return EMPTY
        })
    )

export const sessionEpics = {
    ssoAuthEpic,
    logoutEpic,
    logoutDone,
    loginEpic
}
