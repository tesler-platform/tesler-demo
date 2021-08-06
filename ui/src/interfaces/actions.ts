import { AnyAction as TeslerAnyAction, AnyOfMap, createActionTypes, uActionsMap } from '@tesler-ui/core'
import { Store } from 'redux'
import { ActionsObservable as rActionsObservable } from 'redux-observable'
import { Observable } from 'rxjs/Observable'
import { CustomActionTypes } from '../actions/types'
import { AppState } from '../interfaces/storeSlices'

/**
 * A dictionary storing types of all redux actions available to the application
 */
export const actionTypes = createActionTypes(new CustomActionTypes())

/**
 * Typed actions dictionary
 */
type ActionsMap = uActionsMap<CustomActionTypes>

/**
 * Any action from application or from tesler-ui
 */
export type AnyAction = AnyOfMap<ActionsMap> | TeslerAnyAction

/**
 * Action observable override for typed actions
 */
export interface ActionsObservable<T extends AnyAction> extends rActionsObservable<T> {
    ofType<K extends keyof CustomActionTypes>(...key: K[]): ActionsObservable<ActionsMap[K]>
}

/**
 * redux-observable epic override for typed actions and store
 */
export type CustomEpic = (action$: ActionsObservable<AnyAction>, store: Store<AppState>) => Observable<AnyAction | unknown>
