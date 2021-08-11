import React from 'react'
import { Icon, Popover } from 'antd'
import styles from './Notifications.module.css'

export const Notifications: React.FC = () => {
    return (
        <Popover
            placement="bottomLeft"
            trigger="click"
            content={<p className={styles.content}>No notifications</p>}
            getPopupContainer={element => element.parentElement as HTMLElement}
        >
            <Icon className={styles.icon} type="bell" />
        </Popover>
    )
}

export default React.memo(Notifications)
