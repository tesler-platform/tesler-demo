import React from 'react'
import {$do, connect, useWidgetOperations} from '@tesler-ui/core'
import {Dispatch} from 'redux'
import {Operation, OperationGroup} from '@tesler-ui/core/interfaces/operation'
import {WidgetMeta} from '@tesler-ui/core/interfaces/widget'
import {Button, Dropdown, Icon, Menu} from 'antd'
import { AppState } from '../../interfaces/storeSlices'
import styles from './Operations.module.css'
import cn from 'classnames'

export interface OperationsOwnProps {
    bcName: string,
    widgetMeta: WidgetMeta,
    operations: Array<Operation | OperationGroup>,
    hiddenGroups?: string[],
    formStyle?: boolean,
    screenName?: string,
}

export interface OperationsProps extends OperationsOwnProps {
    metaInProgress: boolean,
    onClick: (bcName: string, operationType: string, widgetName: string) => void,
}

export function Operations(props: OperationsProps) {
    const operations = useWidgetOperations(props.operations, props.widgetMeta)
    .filter(item => item.type !== 'file-upload-save')
    const removeRecordOperation = props.widgetMeta.type === 'List'
    return <div className={styles.container}>
        {(props.metaInProgress)
            ? <Button
                loading
                className={styles.operation}
            />
            : operations.map((item: Operation | OperationGroup, index) => {
                if ((item as OperationGroup).actions) {
                    const group = (item as OperationGroup)
                    if (group.type && props.hiddenGroups && props.hiddenGroups.includes(group.type)) {
                        return null
                    }

                    let groupIsEmpty = true
                    const moreOperations = <Menu>
                        {group.actions.map(operation => {
                            if (removeRecordOperation && operation.scope === 'record') {
                                return null
                            }

                            groupIsEmpty = false
                            return <Menu.Item
                                key={operation.type}
                                className={styles.subOperation}
                                onClick={() => {
                                    props.onClick(
                                        props.bcName,
                                        operation.type,
                                        props.widgetMeta.name
                                    )
                                }}
                            >
                                { operation.icon && <Icon type={operation.icon} className={styles.icon} /> }
                                {operation.text}
                            </Menu.Item>
                        })}
                    </Menu>

                    if (groupIsEmpty) {
                        return null
                    }

                    const trigger = <Button
                        className={cn(
                            styles.operation,
                            {[styles.formOperation]: props.formStyle}
                        )}
                        key={index}
                    >
                        <Icon type="file-add" className={styles.icon}  />
                        {item.text}
                    </Button>

                    return group.actions.length
                        ? <Dropdown
                            trigger={['click']}
                            overlay={moreOperations}
                            key={index}
                            getPopupContainer={element => element.parentElement as HTMLElement}
                        >
                            {trigger}
                        </Dropdown>
                        : trigger
                }

                const ungroupedOperation = (item as Operation)
                return (removeRecordOperation && ungroupedOperation.scope === 'record')
                    ? null
                    : <Button
                        key={index}
                        className={cn(
                            styles.operation,
                            {[styles.formOperation]: props.formStyle && index !== 0},
                        )}
                        onClick={() => {
                            props.onClick(
                                props.bcName,
                                ungroupedOperation.type,
                                props.widgetMeta.name
                            )
                        }}
                    >
                        { ungroupedOperation.icon && <Icon type={ungroupedOperation.icon} className={styles.icon} /> }
                        {item.text}
                    </Button>
            })
        }
    </div>
}

function mapStateToProps(store: AppState, ownProps: OperationsOwnProps) {
    return {
        metaInProgress: !!store.view.metaInProgress[ownProps.bcName],
        screenName: store.screen.screenName,
    }
}

function mapDispatchToProps(dispatch: Dispatch) {
    return {
        onClick: (bcName: string, operationType: string, widgetName: string) => {
            dispatch($do.sendOperation({ bcName, operationType, widgetName }))
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Operations)
