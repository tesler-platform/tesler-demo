import { WidgetMeta, WidgetTypes, WidgetOptions } from '@tesler-ui/core/interfaces/widget'
export enum CustomFieldTypes {
    MultipleSelect = 'multipleSelect'
}

export enum CustomWidgetTypes {
    Steps = 'Steps'
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
