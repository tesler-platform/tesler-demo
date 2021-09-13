import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Pagination as AntPagination } from 'antd'
import styles from './Pagination.module.css'
import { $do } from '../../../actions/types'
import { WidgetTableMeta } from '@tesler-ui/core/interfaces/widget'
import { AppState } from '../../../interfaces/storeSlices'

export interface PaginationProps {
    meta: WidgetTableMeta
}

function Pagination({ meta }: PaginationProps) {
    const dispatch = useDispatch()

    const { bcName, limit: metaLimit } = meta
    const { limit: bcLimit, page } = useSelector((state: AppState) => state.screen.bo.bc[bcName])
    const total = useSelector((state: AppState) => state.view.bcRecordsCount[bcName]?.count)
    const limit = metaLimit || bcLimit

    const handlePageChange = React.useCallback(
        (page: number) => {
            dispatch($do.bcChangePage({ bcName, page }))
        },
        [dispatch, bcName]
    )

    if (!total) {
        return null
    }

    return (
        <AntPagination
            className={styles.pagination}
            size="small"
            pageSize={limit}
            defaultCurrent={page}
            current={page}
            total={total}
            onChange={handlePageChange}
        />
    )
}
export default React.memo(Pagination)
