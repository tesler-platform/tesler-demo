import React from 'react'
import { Popover } from 'antd'
import { useSelector } from 'react-redux'
import { AppState } from '../../../interfaces/storeSlices'
import { ColoredMarkField } from '../../../interfaces/widget'
import styles from './ColoredMark.module.css'

export interface ColoredMarkProps {
    value: string
    cursor: string
    widgetName: string
    meta: ColoredMarkField
}

function ColoredMark({ value, cursor, widgetName, meta }: ColoredMarkProps) {
    const bcName = useSelector((state: AppState) => state.view.widgets?.find(i => i.name === widgetName)?.bcName)
    const data = useSelector((state: AppState) => state.data[bcName!]?.find(item => item.id === cursor))
    const displayedDescription = meta?.hintKey ? data?.[meta.hintKey] : null

    if (!value || !displayedDescription) {
        return null
    }

    return (
        <Popover trigger="hover" content={<p className={styles.markInfo}>{displayedDescription}</p>}>
            <span className={styles.coloredMark} style={{ backgroundColor: value }} />
        </Popover>
    )
}

export default React.memo(ColoredMark)
