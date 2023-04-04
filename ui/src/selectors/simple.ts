import { SimpleSelectProps } from '@interfaces/selectors'
import { PendingValidationFails } from '@tesler-ui/core/interfaces/view'
import { AppState } from '@interfaces/storeSlices'

export const selectSession = (state: AppState) => state.session
export const selectRouter = (state: AppState) => state.router
export const selectScreen = (state: AppState) => state.screen

export const selectBcDictionary = (state: AppState) => state.screen.bo.bc
export const selectBc = (state: AppState, { bcName }: SimpleSelectProps) => state.screen.bo.bc[bcName]

export const selectCachedBcDictionary = (state: AppState) => state.screen.cachedBc
export const selectCachedBc = (state: AppState, { bcName }: SimpleSelectProps) => state.screen.cachedBc[bcName]

export const selectView = (state: AppState) => state.view
export const selectViewWidgets = (state: AppState) => state.view.widgets
export const selectViewInfiniteWidgets = (state: AppState) => state.view.infiniteWidgets

export const selectAllData = (state: AppState) => state.data
export const selectBcData = (state: AppState, { bcName }: SimpleSelectProps) => state.data[bcName]

export const selectAllDepthData = (state: AppState) => state.depthData

export const selectRowMetaDictionary = (state: AppState) => state.view.rowMeta
export const selectBcRowMeta = (state: AppState, { bcName }: SimpleSelectProps) => state.view.rowMeta[bcName]

export const selectMetaInProgressDictionary = (state: AppState) => state.view.metaInProgress
export const selectBcMetaInProgress = (state: AppState, { bcName }: SimpleSelectProps) => state.view.metaInProgress[bcName]

export const selectPendingDataChangesDictionary = (state: AppState) => state.view.pendingDataChanges
export const selectBcPendingDataChanges = (state: AppState, { bcName }: SimpleSelectProps) => state.view.pendingDataChanges[bcName]

export const selectPendingValidationFailsFormat = (state: AppState) => state.view.pendingValidationFailsFormat

export const selectOldPendingValidationFails = (state: AppState, { bcName }: SimpleSelectProps) => state.view.pendingValidationFails
export const selectBcPendingValidationFails = (state: AppState, { bcName }: SimpleSelectProps) =>
    (state.view.pendingValidationFails as PendingValidationFails)?.[bcName]

export const selectHandledForceActiveDictionary = (state: AppState) => state.handledForceActive
export const selectBcHandledForceActive = (state: AppState, { bcName }: SimpleSelectProps) => state.handledForceActive?.[bcName]

export const selectFiltersDictionary = (state: AppState) => state.screen.filters
export const selectBcFilters = (state: AppState, { bcName }: SimpleSelectProps) => state.screen.filters[bcName]

export const selectSortersDictionary = (state: AppState) => state.screen.sorters
export const selectBcSorters = (state: AppState, { bcName }: SimpleSelectProps) => state.screen.sorters[bcName]
