import React from 'react'
import { Layout, Spin } from 'antd'
import AppSide from '../AppSide/AppSide'
import AppBar from '../AppBar/AppBar'
import { useDispatch, useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import DevPanel from '../DevPanel/DevPanel'
import { SSO_AUTH } from '../../actions/types'
import styles from './AppLayout.module.css'
import View from '../View/View'

export const AppLayout: React.FC = () => {
    const sessionActive = useSelector((state: AppState) => state.session.active)
    const logoutRequested = useSelector((state: AppState) => state.session.logout)
    const dispatch = useDispatch()

    React.useEffect(() => {
        if (!sessionActive && !logoutRequested) {
            dispatch({ type: SSO_AUTH })
        }
    }, [sessionActive, logoutRequested, dispatch])

    return sessionActive ? (
        <Layout>
            <DevPanel />
            <Layout className={styles.appLayout}>
                <AppSide />
                <Layout.Content>
                    <AppBar />
                    <View />
                </Layout.Content>
            </Layout>
        </Layout>
    ) : (
        <div className={styles.spinContainer}>
            <Spin size="large" />
        </div>
    )
}

export default React.memo(AppLayout)
