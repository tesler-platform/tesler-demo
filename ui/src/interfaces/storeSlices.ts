import { ScreenState as TeslerScreenState } from '@tesler-ui/core/interfaces/screen'
import { Store } from '@tesler-ui/core/interfaces/store'
import { DataState } from '@tesler-ui/core/interfaces/data'
import { CustomSession } from '../reducers/session'
import { CustomView } from '../reducers/view'

/**
 * You can change typings or add new store slices here
 */
export interface AppReducers extends Partial<Store> {
    screen: ScreenState
    data: DataState
    view: CustomView
    session: CustomSession
}

export type AppState = Store & AppReducers

export interface ScreenState extends TeslerScreenState {
    menuCollapsed: boolean
}
