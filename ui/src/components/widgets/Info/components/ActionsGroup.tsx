import React from 'react'
import { useSelector } from 'react-redux'
import { AppState } from '../../../../interfaces/storeSlices'
import { buildBcUrl, RowOperationsMenu, useWidgetOperations } from '@tesler-ui/core'
import { Button, Dropdown, Icon } from 'antd'
import { WidgetMeta } from '@tesler-ui/core/interfaces/widget'
import styles from './ActionsGroup.module.css'

interface ActionsGroupProps {
    meta: WidgetMeta
}

const ActionsGroup: React.FC<ActionsGroupProps> = props => {
    const { bcName } = props.meta
    const bcUrl = buildBcUrl(bcName, true)
    const actions = useSelector((state: AppState) => state.view.rowMeta[bcName]?.[bcUrl]?.actions)
    const operations = useWidgetOperations(actions, props.meta)

    return (
        <div>
            {operations.length > 0 && (
                <Dropdown
                    placement="bottomLeft"
                    trigger={['click']}
                    getPopupContainer={element => element.parentElement as HTMLElement}
                    overlay={
                        <div className={styles.overlayContainer}>
                            <RowOperationsMenu meta={props.meta} bcName={bcName} />
                        </div>
                    }
                >
                    <Button className={styles.actionsOpenButton}>
                        <Icon type="menu" />
                        <span>Actions</span>
                    </Button>
                </Dropdown>
            )}
        </div>
    )
}

export default ActionsGroup
