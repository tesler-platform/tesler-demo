import {AnyAction} from '../interfaces/actions'
import {AppState} from '../interfaces/storeSlices'
import {ViewState} from '@tesler-ui/core/interfaces/view'

/**
 * Your initial state for this slice
 */
export const initialState: ViewState = {
    rowMeta: {},
    pendingDataChanges: {},
    id: -1,
    name: '',
    url: '',
    handledForceActive: {},
    metaInProgress: {},
    widgets: [],
    columns: null,
    rowHeight: null,
    readOnly: false,
    popupData: { bcName: '' }
}

export default function viewReducer(
    state: ViewState = initialState,
    action: AnyAction,
    store?: Readonly<AppState>
): ViewState {
    switch (action.type) {
        /**
         * Your reducers for this slice
         */
        default:
            return state
    }
}
