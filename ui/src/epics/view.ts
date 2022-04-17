import { CustomEpic, actionTypes } from '../interfaces/actions'
import { Observable } from 'rxjs/Observable'
import { $do } from '../actions/types'
import { buildBcUrl } from '@tesler-ui/core'
import { fetchBcCount } from '../api/bcCount'

const bcFetchCountEpic: CustomEpic = (action$, store) =>
    action$
        .ofType(actionTypes.bcFetchDataRequest)
        .mergeMap(action => {
            const state = store.getState()
            const { widgetName } = action.payload
            const widget = state.view.widgets.find(i => i.name === widgetName)
            if (!widget) {
                return Observable.empty()
            }
            const bcName = widget.bcName
            const filters = state.screen.filters[bcName]
            const bcUrl = buildBcUrl(bcName)
            return fetchBcCount(bcUrl, filters).mergeMap(({ data }) =>
                Observable.of(
                    $do.setBcCount({
                        bcName,
                        count: data
                    })
                )
            )
        })
        .catch(error => {
            console.error(error)
            return Observable.empty<never>()
        })

export const viewEpics = {
    bcFetchCountEpic
}
