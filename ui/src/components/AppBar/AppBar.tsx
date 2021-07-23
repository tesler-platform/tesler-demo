import React from 'react'
import { ViewNavigation } from '../ViewNavigation/ViewNavigation'
import UserMenu from './components/UserMenu/UserMenu'
import styles from './AppBar.module.css'
import { useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import { WidgetTypes } from '@tesler-ui/schema'
import { Layout } from 'antd'

function AppBar() {
    const widgets = useSelector((state: AppState) => state.view.widgets)
    const showTabs = widgets?.some(i => i.type === WidgetTypes.SecondLevelMenu)
    return (
        <Layout.Header className={styles.container}>
            <div>{showTabs && <ViewNavigation />}</div>
            <UserMenu />
        </Layout.Header>
    )
}

export default React.memo(AppBar)
