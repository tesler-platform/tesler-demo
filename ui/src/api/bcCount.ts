import { buildUrl, axiosGet } from '@tesler-ui/core'
import qs from 'query-string'
import { BcCountParamsMap, BcCountResponse } from '../interfaces/bcCount'

export function fetchBcCount(bcName: string, params: BcCountParamsMap = {}) {
    const url = buildUrl`count/dashboard/` + bcName
    const stringParams = qs.stringify(params, { encode: true })

    return axiosGet<BcCountResponse>(url + (stringParams && `?${stringParams}`))
}
