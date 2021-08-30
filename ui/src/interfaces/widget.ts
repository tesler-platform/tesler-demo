import { WidgetFieldBase } from '@tesler-ui/core/interfaces/widget'

export enum CustomFieldTypes {
    ColoredMark = 'coloredMark',
    MultipleSelect = 'multipleSelect'
}

export enum CustomWidgetTypes {
    Header = 'Header'
}

export type ColoredMarkField = WidgetFieldBase & {
    hintKey?: string
}
