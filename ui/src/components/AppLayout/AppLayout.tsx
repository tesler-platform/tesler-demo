import React from 'react'
import { Layout, Spin } from 'antd'
import { View } from '@tesler-ui/core'
import AppSider from '../AppSider/AppSider'
import AppBar from '../AppBar/AppBar'
import { useDispatch, useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import { Card } from '../Card/Card'
import DevPanel from '../DevPanel/DevPanel'
import { SSO_AUTH } from '../../actions/types'
import styles from './AppLayout.module.css'
import {CustomFieldTypes} from "../../interfaces/widget";
import MultipleSelectField from "../../fields/MultipleSelectField/MultipleSelectField";

const skipWidgetTypes = [
    'HeaderWidget',
    'SecondLevelMenu'
]

const customFields = {
    [CustomFieldTypes.MultipleSelect] : MultipleSelectField
}

export const AppLayout: React.FC = () => {
    const sessionActive = useSelector((state: AppState) => state.session.active)
    const logoutRequested = useSelector((state: AppState) => state.session.logout)
    const dispatch = useDispatch()

    React.useEffect(() => {
        if (!sessionActive && !logoutRequested) {
            dispatch({type: SSO_AUTH})
        }
    }, [sessionActive, logoutRequested, dispatch])

    return sessionActive
        ? <Layout>
            <DevPanel/>
            <Layout>
                <Layout.Sider>
                    <AppSider/>
                </Layout.Sider>
                <Layout.Content>
                    <Layout.Header>
                        <AppBar/>
                    </Layout.Header>
                    <View customFields={customFields} card={ Card as any } skipWidgetTypes={ skipWidgetTypes }/>
                </Layout.Content>
            </Layout></Layout>
        : <div className={styles.spinContainer}><Spin size="large"/></div>
}

export default React.memo(AppLayout)
