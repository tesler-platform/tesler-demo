import React from 'react'
import { Row, Col } from 'antd'
import { Widget } from '@teslerComponents'
import { WidgetErrorBoundary } from '@teslerComponents'
import { CustomWidgetDescriptor, WidgetMeta } from '@tesler-ui/core/interfaces/widget'

export interface DashboardLayoutProps {
    widgets: WidgetMeta[]
    customWidgets?: Record<string, CustomWidgetDescriptor>
    skipWidgetTypes?: string[]
    customSpinner?: (props: any) => React.ReactElement<any>
    card?: (props: any) => React.ReactElement<any>
}

export function DashboardLayout(props: DashboardLayoutProps) {
    const widgetsByRow = React.useMemo(() => {
        return groupByRow(props.widgets, props.skipWidgetTypes || [])
    }, [props.widgets, props.skipWidgetTypes])
    return (
        <React.Fragment>
            {Object.values(widgetsByRow).map((row, rowIndex) => (
                <Row key={rowIndex}>
                    {row.map((widget, colIndex) => (
                        <Col key={colIndex} span={widget.gridWidth}>
                            <WidgetErrorBoundary meta={widget}>
                                <Widget
                                    meta={widget}
                                    card={props.card}
                                    customWidgets={props.customWidgets}
                                    customSpinner={props.customSpinner}
                                />
                            </WidgetErrorBoundary>
                        </Col>
                    ))}
                </Row>
            ))}
        </React.Fragment>
    )
}

function groupByRow(widgets: WidgetMeta[], skipWidgetTypes: string[]) {
    const byRow: Record<string, WidgetMeta[]> = {}
    widgets
        .filter(item => {
            return !skipWidgetTypes.includes(item.type)
        })
        .forEach(item => {
            if (!byRow[item.position]) {
                byRow[item.position] = []
            }
            byRow[item.position].push(item)
        })
    return byRow
}

export const MemoizedDashboard = React.memo(DashboardLayout)

export default MemoizedDashboard
