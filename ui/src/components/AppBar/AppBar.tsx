import React from 'react'
import { ViewNavigation } from '../ViewNavigation/ViewNavigation'
import UserMenu from './components/UserMenu/UserMenu'
import Notifications from './components/Notifications/Notifications'
import styles from './AppBar.module.css'
import { useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import { WidgetTypes } from '@tesler-ui/schema'
import { Layout } from 'antd'
import cn from 'classnames'

function AppBar() {
    const widgets = useSelector((state: AppState) => state.view.widgets)
    const showTabs = widgets?.some(i => i.type === WidgetTypes.SecondLevelMenu)
    return (
        <Layout.Header className={cn(styles.container, { [styles.withTabs]: showTabs })}>
            {showTabs && <ViewNavigation />}
            <div className={styles.rightBlock}>
                <Notifications />
                <UserMenu />
            </div>
        </Layout.Header>
    )
}

export default React.memo(AppBar)
