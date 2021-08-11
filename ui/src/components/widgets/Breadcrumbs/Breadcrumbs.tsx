import React from 'react'
import { Breadcrumb as UiBreadcrumb } from 'antd'
import Breadcrumb from './Breadcrumb'
import { WidgetBreadcrumbsMeta } from '../../../interfaces/widget'
import styles from './Breadcrumbs.module.css'

interface BreadcrumbsWidgetProps {
    meta: WidgetBreadcrumbsMeta
}

const Breadcrumbs: React.FunctionComponent<BreadcrumbsWidgetProps> = props => {
    const breadcrumbsConfig = (props.meta.options && props.meta.options.breadcrumbs) || []

    return (
        <UiBreadcrumb className={styles.container}>
            {breadcrumbsConfig.map(item => {
                return (
                    <UiBreadcrumb.Item key={`${item.viewName}_${item.bcName}_${item.type}`}>
                        <Breadcrumb config={item} />
                    </UiBreadcrumb.Item>
                )
            })}
        </UiBreadcrumb>
    )
}

export default React.memo(Breadcrumbs)
