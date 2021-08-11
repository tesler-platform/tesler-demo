import { CustomEpic, actionTypes } from '../interfaces/actions'
import { Observable } from 'rxjs/Observable'
import { $do } from '../actions/types'
import { BcCountResponse } from '../interfaces/bcCount'
import { buildBcUrl } from '@tesler-ui/core'
import { fetchBcCount } from '../api/bcCount'

const bcFetchCountEpic: CustomEpic = (action$, store) =>
    action$
        .ofType(actionTypes.bcFetchDataRequest)
        .mergeMap(action => {
            const bcName = action.payload.bcName as string
            const state = store.getState()
            const filters = state.screen.filters[bcName]
            const bcUrl = buildBcUrl(bcName)
            return fetchBcCount(bcUrl, filters).mergeMap(({ data }: BcCountResponse) =>
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
