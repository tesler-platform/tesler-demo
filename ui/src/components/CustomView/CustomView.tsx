import React from 'react'
import { CustomWidgetDescriptor } from '@tesler-ui/core/interfaces/widget'
import styles from './CustomView.module.css'
import { DashboardLayout } from '@components/ui/DashboardLayout/DashboardLayout'
import { customFields, customWidgets, skipWidgetTypes } from '@constants/view'
import { View } from '@tesler-ui/core'
import Card from '@components/Card/Card'

function CustomView() {
    return (
        <div className={styles.container}>
            <View
                customWidgets={customWidgets as Record<string, CustomWidgetDescriptor>}
                customFields={customFields}
                skipWidgetTypes={skipWidgetTypes}
                customLayout={DashboardLayout}
                card={Card as any}
            />
        </div>
    )
}

export default React.memo(CustomView)
