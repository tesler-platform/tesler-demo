import {ScreenState as TeslerScreenState} from '@tesler-ui/core/interfaces/screen'
import {Store} from '@tesler-ui/core/interfaces/store'
import {DataState} from '@tesler-ui/core/interfaces/data'
import {ViewState} from '@tesler-ui/core/interfaces/view'
import {CustomSession} from '../reducers/session'

/**
 * You can change typings or add new store slices here
 */
export interface AppReducers extends Partial<Store> {
    screen: ScreenState,
    data: DataState,
    view: ViewState,
    session: CustomSession
}

export type AppState = Store & AppReducers

export interface ScreenState extends TeslerScreenState {

}
