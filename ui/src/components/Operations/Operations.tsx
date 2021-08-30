import React from 'react'
import { $do, useWidgetOperations } from '@tesler-ui/core'
import { Operation, OperationGroup } from '@tesler-ui/core/interfaces/operation'
import { WidgetMeta, WidgetTypes } from '@tesler-ui/core/interfaces/widget'
import { Button, Dropdown, Icon, Menu } from 'antd'
import { AppState } from '../../interfaces/storeSlices'
import styles from './Operations.module.css'
import OperationButton from '../ui/OperationButton/OperationButton'
import { useDispatch, useSelector } from 'react-redux'

export interface OperationsOwnProps {
    bcName: string
    widgetMeta: WidgetMeta
    operations: Array<Operation | OperationGroup>
    hiddenGroups?: string[]
}

// todo refactor
export function Operations(props: OperationsOwnProps) {
    const { bcName, widgetMeta, operations, hiddenGroups } = props
    const metaInProgress = useSelector((store: AppState) => store.view.metaInProgress[bcName])
    const dispatch = useDispatch()
    const currentOperations = useWidgetOperations(operations, widgetMeta).filter(item => item.type !== 'file-upload-save')
    const removeRecordOperation = widgetMeta.type === WidgetTypes.List
    const createClickHandler = React.useCallback(
        (operation: Operation) => () => {
            dispatch(
                $do.sendOperation({
                    bcName,
                    operationType: operation.type,
                    widgetName: widgetMeta.name,
                    bcKey: operation.bcKey,
                    confirmOperation: operation.preInvoke
                })
            )
        },
        [dispatch, bcName, widgetMeta]
    )
    return (
        <div className={styles.container}>
            {metaInProgress ? (
                <Button loading type="link" className={styles.loading} />
            ) : (
                currentOperations.map((item: Operation | OperationGroup, index) => {
                    if ((item as OperationGroup).actions) {
                        const group = item as OperationGroup
                        if (group.type && hiddenGroups && hiddenGroups.includes(group.type)) {
                            return null
                        }

                        let groupIsEmpty = true
                        const moreOperations = (
                            // todo styles
                            <Menu>
                                {group.actions.map(operation => {
                                    if (removeRecordOperation && operation.scope === 'record') {
                                        return null
                                    }

                                    groupIsEmpty = false
                                    return (
                                        <Menu.Item
                                            key={operation.type}
                                            className={styles.subOperation}
                                            onClick={createClickHandler(operation)}
                                        >
                                            {operation.icon && <Icon type={operation.icon} />}
                                            {operation.text}
                                        </Menu.Item>
                                    )
                                })}
                            </Menu>
                        )

                        if (groupIsEmpty) {
                            return null
                        }

                        const trigger = (
                            <OperationButton key={item.text}>
                                <Icon type="file-add" />
                                {item.text}
                            </OperationButton>
                        )

                        return group.actions.length ? (
                            <Dropdown
                                trigger={['click']}
                                overlay={moreOperations}
                                key={index}
                                getPopupContainer={element => element.parentElement as HTMLElement}
                            >
                                {trigger}
                            </Dropdown>
                        ) : (
                            trigger
                        )
                    }

                    const ungroupedOperation = item as Operation
                    return removeRecordOperation && ungroupedOperation.scope === 'record' ? null : (
                        <OperationButton key={ungroupedOperation.type} onClick={createClickHandler(ungroupedOperation)}>
                            {ungroupedOperation.icon && <Icon type={ungroupedOperation.icon} />}
                            {item.text}
                        </OperationButton>
                    )
                })
            )}
        </div>
    )
}

export default React.memo(Operations)
