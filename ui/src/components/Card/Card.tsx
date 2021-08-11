import React from 'react'
import { buildBcUrl, TemplatedTitle } from '@tesler-ui/core'
import { WidgetMeta, WidgetTypes } from '@tesler-ui/core/interfaces/widget'
import { Col, Row } from 'antd'
import Operations from '../Operations/Operations'
import { useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import styles from './Card.module.css'

export interface CardProps {
    children: React.ReactNode
    meta: WidgetMeta
}
const showOperations = [WidgetTypes.List, WidgetTypes.DataGrid, WidgetTypes.Form]

function Card({ meta, children }: CardProps) {
    const { type, bcName } = meta
    const bcUrl = useSelector((state: AppState) => state.screen.bo.bc[bcName] && buildBcUrl(bcName, true))
    const operations = useSelector((state: AppState) => state.view.rowMeta?.[bcName]?.[bcUrl]?.actions)

    return (
        <Row justify="center">
            <Col span={22}>
                <div className={styles.container}>
                    {meta.title && (
                        <h2 className={styles.widgetTitle}>
                            <TemplatedTitle widgetName={meta.name} title={meta.title} />
                        </h2>
                    )}
                    {type === WidgetTypes.Form && children}
                    {showOperations.includes(type as WidgetTypes) && (
                        <Operations
                            operations={operations}
                            bcName={bcName}
                            widgetMeta={meta}
                            hiddenGroups={meta.options?.hideActionGroups}
                        />
                    )}
                    {type !== WidgetTypes.Form && children}
                </div>
            </Col>
        </Row>
    )
}

export default React.memo(Card)
