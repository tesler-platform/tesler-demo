import React from 'react'
import { Layout, Spin } from 'antd'
import AppSide from '../AppSide/AppSide'
import AppBar from '../AppBar/AppBar'
import { useSelector } from 'react-redux'
import { AppState } from '@interfaces/storeSlices'
import DevPanel from '../DevPanel/DevPanel'
import styles from './AppLayout.module.css'
import View from '../CustomView/CustomView'
import ModalInvoke from '../ModalInvoke/ModalInvoke'
import SystemNotifications from '../SystemNotifications/SystemNotifications'
import { useSsoAuth } from '../../hooks'

export const AppLayout = () => {
    const modalInvoke = useSelector((state: AppState) => state.view.modalInvoke)
    const { sessionActive } = useSsoAuth()

    return sessionActive ? (
        <Layout>
            <DevPanel />
            {modalInvoke?.operation && <ModalInvoke />}
            <SystemNotifications />
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
