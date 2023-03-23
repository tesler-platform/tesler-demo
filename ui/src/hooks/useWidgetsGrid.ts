import { useMemo } from 'react'
import { WidgetMeta } from '@tesler-ui/core/interfaces/widget'

function createWidgetsGrid(widgets: WidgetMeta[], skipWidgetTypes: string[]) {
    const grid: Record<string, WidgetMeta[]> = {}

    widgets
        .filter(widget => {
            return !skipWidgetTypes.includes(widget.type)
        })
        .forEach(widget => {
            if (!grid[widget.position]) {
                grid[widget.position] = []
            }

            grid[widget.position].push(widget)
        })

    return Object.values(grid)
}

export function useWidgetsGrid(widgets: WidgetMeta[], skipWidgetTypes: string[] = []) {
    return useMemo(() => createWidgetsGrid(widgets, skipWidgetTypes), [widgets, skipWidgetTypes])
}
