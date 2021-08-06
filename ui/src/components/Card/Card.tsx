import React from 'react'
import { buildBcUrl, TemplatedTitle, WidgetTypes } from '@tesler-ui/core'
import { WidgetMeta } from '@tesler-ui/core/interfaces/widget'
import { Col, Row } from 'antd'
import Operations from '../Operations/Operations'
import { useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import styles from './Card.module.css'

export interface CardOwnProps {
    children: React.ReactNode,
    meta: WidgetMeta
}
const showOperations = [WidgetTypes.List, WidgetTypes.DataGrid, WidgetTypes.Form]

export const Card: React.FC<CardOwnProps> = (props) => {

    const {meta} = props
    const {type, bcName} = meta
    const bcUrl = useSelector((state: AppState) => state.screen.bo.bc[bcName] && buildBcUrl(bcName, true))
    const operations = useSelector((state: AppState) => state.view.rowMeta?.[bcName]?.[bcUrl]?.actions)

    return <Row justify="center">
        <Col span={ 22 } offset={1}>
            <div className={styles.container}>
                <h2><TemplatedTitle widgetName={ meta.name } title={ meta.title }/></h2>
                { type === WidgetTypes.Form && props.children }
                {showOperations.includes(type as WidgetTypes)
                && <Operations
                    operations={operations}
                    bcName={bcName}
                    widgetMeta={meta}
                    hiddenGroups={props.meta.options && props.meta.options.hideActionGroups}
                    formStyle={type === WidgetTypes.Form}
                />
                }
                { type !== WidgetTypes.Form && props.children }
            </div>
        </Col>
    </Row>
}
