import { WidgetMeta, WidgetTypes } from '@tesler-ui/core/interfaces/widget'
import { WidgetOptions } from '@tesler-ui/schema'
export enum CustomFieldTypes {
    MultipleSelect = 'multipleSelect'
}

export enum CustomWidgetTypes {
    Steps = 'Steps',
    EmptyWidget = 'Empty'
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
