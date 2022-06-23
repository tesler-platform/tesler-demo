import { AnyAction as TeslerAnyAction, AnyOfMap, createActionTypes, uActionsMap } from '@tesler-ui/core'
import { StateObservable } from 'redux-observable'
import { Observable } from 'rxjs'
import { CustomActionTypes } from '../actions/types'
import { AppState } from './storeSlices'
import { EpicDependencies } from '@tesler-ui/core/actions/actions'

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
 * redux-observable epic override for typed actions and store
 */
export type CustomEpic<
    Input extends AnyAction = any,
    Output extends Input | unknown = Input | unknown,
    State = AppState,
    Dependencies = EpicDependencies
> = (action$: Observable<Input>, state$: StateObservable<State>, dependencies?: Dependencies) => Observable<Output>
