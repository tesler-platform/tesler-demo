import React from 'react'
import { Row, Col } from 'antd'
import { CustomWidgetDescriptor, WidgetMeta } from '@tesler-ui/core/interfaces/widget'
import { useWidgetsGrid } from '@hooks/useWidgetsGrid'
import { Widget, WidgetErrorBoundary } from '@tesler-ui/core'

export interface DashboardLayoutProps {
    widgets: WidgetMeta[]
    customWidgets?: Record<string, CustomWidgetDescriptor>
    skipWidgetTypes?: string[]
    customSpinner?: (props: any) => React.ReactElement<any>
    card?: (props: any) => React.ReactElement<any>
}

export function DashboardLayout({ widgets, skipWidgetTypes, customWidgets, customSpinner, card }: DashboardLayoutProps) {
    const widgetsGrid = useWidgetsGrid(widgets, skipWidgetTypes)

    return (
        <React.Fragment>
            {widgetsGrid.map((row, rowIndex) => (
                <Row key={rowIndex}>
                    {row.map((widget, colIndex) => (
                        <Col key={colIndex} span={widget.gridWidth}>
                            <WidgetErrorBoundary meta={widget}>
                                <Widget meta={widget} customWidgets={customWidgets} card={card} customSpinner={customSpinner} />
                            </WidgetErrorBoundary>
                        </Col>
                    ))}
                </Row>
            ))}
        </React.Fragment>
    )
}

export default React.memo(DashboardLayout)
