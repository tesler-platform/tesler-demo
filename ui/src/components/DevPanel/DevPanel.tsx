import React from 'react'
import { DevToolsPanel } from '@tesler-ui/core'
import { useSelector } from 'react-redux';
import { AppState } from '../../interfaces/storeSlices'

const DevPanel: React.FunctionComponent = () => {
    const showCondition = useSelector((state: AppState) => state.session.devPanelEnabled)
    return (
        <>
            {showCondition && (
                <DevToolsPanel/>
            )}
        </>
    )
}

export default React.memo(DevPanel)
