
import React from 'react'
import {Menu, Icon} from 'antd'
import {changeLocation} from '@tesler-ui/core'
import {SessionScreen} from '@tesler-ui/core/interfaces/session'
import styles from './ScreenNavigation.module.css'
import { ClickParam } from 'antd/lib/menu'

export interface ScreenNavigationProps {
    items: SessionScreen[],
    selectedScreen: string
}

export function ScreenNavigation(props: ScreenNavigationProps) {
    const screens: SessionScreen[] = props.items || []
    const handleScreen = (e: ClickParam) => {
        changeLocation(e.key)
    }

    return <Menu
        className={styles.Container}
        selectedKeys={[props.selectedScreen]}
        onClick={handleScreen}
        theme="dark"
    >
        {screens.map((item) => {
            return (
                <Menu.Item
                    key={item.url}
                    className={styles.Item}
                >
                    <span className={styles.MenuItemLink}>
                        <Icon className={styles.icon} type={item.icon ? item.icon : 'coffee'} />
                        <span>{item.text}</span>
                    </span>
                </Menu.Item>
            )
        })}
    </Menu>
}

export default React.memo(ScreenNavigation)
