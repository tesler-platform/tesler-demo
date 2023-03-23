import { CustomWidgetDescriptor, WidgetTypes } from '@tesler-ui/core/interfaces/widget'
import { FieldType } from '@tesler-ui/core/interfaces/view'
import Dictionary from '../fields/Dictionary/Dictionary'
import { CustomFieldTypes, CustomWidgetTypes } from '@interfaces/widget'
import MultipleSelectField from '../fields/MultipleSelectField/MultipleSelectField'
import Info from '@components/widgets/Info/Info'
import Table from '@components/widgets/Table/Table'
import Header from '@components/widgets/Header/Header'
import EmptyCard from '@components/EmptyCard/EmptyCard'
import Steps from '@components/widgets/Steps/Steps'
import Funnel from '@components/widgets/Funnel/Funnel'
import DashboardCard from '@components/DashboardCard/DashboardCard'
import RingProgress from '@components/widgets/RingProgress/RingProgress'
import DashboardList from '@components/widgets/DashboardList/DashboardList'
import AssocListPopup from '@components/widgets/AssocListPopup/AssocListPopup'
import PickListPopup from '@components/widgets/PickListPopup/PickListPopup'
import FormWidget from '@components/widgets/FormWidget/FormWidget'

export const skipWidgetTypes = [WidgetTypes.SecondLevelMenu]

export const customFields = {
    [FieldType.dictionary]: Dictionary,
    [CustomFieldTypes.MultipleSelect]: MultipleSelectField
}

export const customWidgets: Partial<Record<CustomWidgetTypes | WidgetTypes, CustomWidgetDescriptor>> = {
    [WidgetTypes.Form]: { component: FormWidget },
    [WidgetTypes.Info]: { component: Info },
    [WidgetTypes.List]: { component: Table },
    [WidgetTypes.HeaderWidget]: { component: Header, card: EmptyCard },
    [CustomWidgetTypes.Steps]: { component: Steps, card: EmptyCard },
    [CustomWidgetTypes.Funnel]: { component: Funnel, card: DashboardCard },
    [CustomWidgetTypes.RingProgress]: { component: RingProgress, card: DashboardCard },
    [CustomWidgetTypes.DashboardList]: { component: DashboardList, card: DashboardCard },
    [WidgetTypes.AssocListPopup]: AssocListPopup,
    [WidgetTypes.PickListPopup]: PickListPopup
}
