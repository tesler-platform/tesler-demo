import React from 'react'
import {useSelector} from 'react-redux'
import { ScreenNavigation } from '../ScreenNavigation/ScreenNavigation'
import { AppState } from '../../interfaces/storeSlices'

export const AppSider: React.FC = () => {
    const sessionScreens = useSelector((state: AppState) => state.session.screens)
    const screenName = useSelector((state: AppState) => state.router.screenName)
    const selectedScreen = sessionScreens.find(item => item.name === screenName)
    const screenUrl = selectedScreen?.url ?? `/screen/${screenName}`

    return <div>
        <ScreenNavigation items={sessionScreens} selectedScreen={screenUrl} />
    </div>
}

export default React.memo(AppSider)
