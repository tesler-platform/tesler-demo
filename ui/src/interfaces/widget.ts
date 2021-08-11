import { WidgetFieldBase, WidgetMeta, WidgetOptions } from '@tesler-ui/core/interfaces/widget'

export enum CustomFieldTypes {
    MultipleSelect = 'multipleSelect',
    ColoredMark = 'coloredMark'
}

export enum CustomWidgetTypes {
    Header = 'Header',
    Breadcrumbs = 'Breadcrumbs'
}

export type ColoredMarkField = WidgetFieldBase & {
    hintKey?: string
}

export interface Breadcrumb {
    viewName: string
    bcName: string
    type: 'list' | 'card'
}

export interface WidgetBreadcrumbsMeta extends WidgetMeta {
    type: CustomWidgetTypes.Breadcrumbs
    options?: WidgetOptions & { breadcrumbs: Breadcrumb[] }
}

export interface CustomWidgetTableMeta extends WidgetMeta {
    options?: WidgetOptions & { maxWidth: number }
}
