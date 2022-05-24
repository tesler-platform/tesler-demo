import { EMPTY, of as observableOf } from 'rxjs'
import { catchError, mergeMap } from 'rxjs/operators'
import { CustomEpic, actionTypes } from '../interfaces/actions'
import { $do } from '../actions/types'
import { buildBcUrl, getFilters } from '@tesler-ui/core'
import { fetchBcCount } from '../api/bcCount'
import { ofType } from 'redux-observable'
import { EMPTY_ARRAY } from '../constants/constants'

const bcFetchCountEpic: CustomEpic = (action$, store$) =>
    action$.pipe(
        ofType(actionTypes.bcFetchDataSuccess),
        mergeMap(action => {
            const state = store$.value
            const sourceWidget = state.view.widgets?.find(i => i.bcName === action.payload.bcName)

            if (!sourceWidget) {
                return EMPTY
            }

            const bcName = sourceWidget.bcName
            const filters = getFilters(state.screen.filters[bcName] || EMPTY_ARRAY)
            const bcUrl = buildBcUrl(bcName)
            return fetchBcCount(bcUrl, filters).pipe(
                mergeMap(({ data }) =>
                    observableOf(
                        $do.setBcCount({
                            bcName,
                            count: data
                        })
                    )
                )
            )
        }),
        catchError(error => {
            console.error(error)
            return EMPTY
        })
    )

export const viewEpics = {
    bcFetchCountEpic
}
