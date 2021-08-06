import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import ScreenNavigation from '../ScreenNavigation/ScreenNavigation'
import { AppState } from '../../interfaces/storeSlices'
import { Layout } from 'antd'
import logo from '../../assets/icons/logo.svg'
import logoWide from '../../assets/icons/logo-wide.svg'
import { $do } from '../../actions/types'
import styles from './AppSide.module.css'
import { SessionScreen } from '@tesler-ui/core/interfaces/session'

function AppSide() {
    const dispatch = useDispatch()
    const menuCollapsed = useSelector((state: AppState) => state.screen.menuCollapsed)
    const handleMenuCollapse = React.useCallback(() => {
        dispatch($do.changeMenuCollapsed(!menuCollapsed))
    }, [dispatch, menuCollapsed])
    const sessionScreens = useSelector((state: AppState) => state.session.screens)
    const screenName = useSelector((state: AppState) => state.router.screenName)
    const selectedScreen =
        sessionScreens.find(item => item.name === screenName) ??
        (sessionScreens.find((screen: SessionScreen) => screen.defaultScreen) || sessionScreens[0])
    const screenUrl = selectedScreen?.url ?? `/screen/${screenName}`

    return (
        <Layout.Sider theme="light" collapsed={menuCollapsed} className={styles.side}>
            <div className={styles.logoContainer}>
                <img src={menuCollapsed ? logo : logoWide} onClick={handleMenuCollapse} alt="logo" />
            </div>
            <ScreenNavigation items={sessionScreens} selectedScreen={screenUrl} />
        </Layout.Sider>
    )
}

export default React.memo(AppSide)
