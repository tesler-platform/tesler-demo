import { WidgetMeta, WidgetTypes, WidgetOptions } from '@tesler-ui/core/interfaces/widget'
import { WidgetFormFieldBase } from '@tesler-ui/schema/src/interfaces/widget'

export enum CustomFieldTypes {
    MultipleSelect = 'multipleSelect',
    Proposals = 'proposals'
}

export enum CustomWidgetTypes {
    Steps = 'Steps',
    Funnel = 'Funnel',
    RingProgress = 'RingProgress',
    DashboardList = 'DashboardList'
}

export const removeRecordOperationWidgets: Array<WidgetTypes | string> = [WidgetTypes.List]

export interface StepsWidgetMeta extends WidgetMeta {
    type: CustomWidgetTypes.Steps
    options: WidgetOptions & {
        stepsOptions: {
            stepsDictionaryKey: string
        }
    }
}

export interface FunnelWidgetMeta extends WidgetMeta {
    type: CustomWidgetTypes.Funnel
    options: WidgetOptions & { funnelOptions: { dataKey: string } }
}

export interface RingProgressWidgetMeta extends WidgetMeta {
    type: CustomWidgetTypes.RingProgress
    options: WidgetOptions & { ringProgressOptions: { text: string; numberField: string; descriptionField: string; percentField: string } }
}

export interface ProposalsWidgetField extends Omit<WidgetFormFieldBase, 'type'> {
    type: CustomFieldTypes.Proposals
    proposalsMap?: Record<string, string>
    endpoint?: string
    searchParamName?: string
    uniqueValueKey: string
    optionsView?: {
        mainLabel?: string
        additionalView?: Array<{ fieldKey?: string; dataKey?: string; label?: string }>
    }
}
