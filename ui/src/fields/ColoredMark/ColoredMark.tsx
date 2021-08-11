import React from 'react'
import { Popover } from 'antd'
import { useSelector } from 'react-redux'
import { AppState } from '../../interfaces/storeSlices'
import { ColoredMarkField } from '../../interfaces/widget'
import { DataItem } from '@tesler-ui/core/interfaces/data'
import styles from './ColoredMark.module.css'

export interface ColoredMarkProps {
    value: string
    cursor: string
    widgetName: string
    meta: ColoredMarkField
}

const ColoredMark: React.FC<ColoredMarkProps> = ({ value, cursor, widgetName, meta }) => {
    const bcName = useSelector((state: AppState) => state.view.widgets?.find(i => i.name === widgetName)?.bcName)
    const data = useSelector((state: AppState) => bcName && state.data[bcName]?.find(item => item.id === cursor)) as DataItem

    if (!value) {
        return null
    }
    const displayedDescription = meta?.hintKey ? data?.[meta.hintKey] : null

    return (
        <div>
            {displayedDescription ? (
                <Popover trigger="hover" content={<p className={styles.markInfo}>{displayedDescription}</p>}>
                    <span className={styles.coloredMark} style={{ backgroundColor: value, cursor: 'pointer' }} />
                </Popover>
            ) : (
                <span className={styles.coloredMark} style={{ backgroundColor: value }} />
            )}
        </div>
    )
}

export default React.memo(ColoredMark)
