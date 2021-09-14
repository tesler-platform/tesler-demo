import React from 'react'
import { CustomWidgetDescriptor, WidgetTypes } from '@tesler-ui/core/interfaces/widget'
import Card from '../Card/Card'
import { View as TeslerView } from '@tesler-ui/core'
import { CustomFieldTypes, CustomWidgetTypes } from '../../interfaces/widget'
import MultipleSelectField from '../../fields/MultipleSelectField/MultipleSelectField'
import Form from '../widgets/Form/Form'
import Header from '../widgets/Header/Header'
import EmptyCard from '../EmptyCard/EmptyCard'
import styles from './View.module.css'
import Info from '../widgets/Info/Info'
import Table from '../widgets/Table/Table'
import { FieldType } from '@tesler-ui/core/interfaces/view'
import Dictionary from '../../fields/Dictionary/Dictionary'
import Steps from '../widgets/Steps/Steps'

const skipWidgetTypes = [WidgetTypes.SecondLevelMenu]

const customFields = {
    [FieldType.dictionary]: Dictionary,
    [CustomFieldTypes.MultipleSelect]: MultipleSelectField
}

const customWidgets: Partial<Record<CustomWidgetTypes | WidgetTypes, CustomWidgetDescriptor>> = {
    [WidgetTypes.Form]: { component: Form },
    [WidgetTypes.Info]: { component: Info },
    [WidgetTypes.List]: { component: Table },
    [WidgetTypes.HeaderWidget]: { component: Header, card: EmptyCard },
    [CustomWidgetTypes.Steps]: { component: Steps, card: EmptyCard }
}

function View() {
    return (
        <div className={styles.container}>
            <TeslerView
                customWidgets={customWidgets as Record<string, CustomWidgetDescriptor>}
                customFields={customFields}
                card={Card as any}
                skipWidgetTypes={skipWidgetTypes}
            />
        </div>
    )
}

export default React.memo(View)
