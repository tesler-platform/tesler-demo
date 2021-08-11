import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { AppState } from '../../../interfaces/storeSlices'
import { $do, ActionLink, buildBcUrl } from '@tesler-ui/core'
import { Breadcrumb as BreadcrumbType } from '../../../interfaces/widget'
import styles from './Breadcrumbs.module.css'

interface BreadcrumbProps {
    config: BreadcrumbType
}

const Breadcrumb: React.FC<BreadcrumbProps> = props => {
    const dispatch = useDispatch()

    const config = props.config

    const view = useSelector((state: AppState) => state.screen.views.find(v => v.name === config.viewName))
    const bc = useSelector((state: AppState) => state.screen.bo.bc[config.bcName])
    const route = useSelector((state: AppState) => state.router)
    const url = view && `${view.url}/${buildBcUrl(config.bcName, config.type === 'card')}`
    const viewTitle = view && `${view.title}${bc.cursor && config.type === 'card' ? ` ${bc.cursor}` : ''}`

    const handleClick = React.useCallback(() => url && dispatch($do.drillDown({ url, route })), [dispatch, url, route])

    if (!view || !bc) {
        return null
    }

    return (
        <ActionLink onClick={handleClick} className={styles.breadcrumbItem}>
            <span>{viewTitle}</span>
        </ActionLink>
    )
}

export default React.memo(Breadcrumb)
