import React from 'react'
import { Col, Row } from 'antd'

interface EmptyCardProps {
    children?: React.ReactNode
}

function EmptyCard({ children }: EmptyCardProps) {
    return (
        <Row justify="center">
            <Col span={22} offset={1}>
                {children}
            </Col>
        </Row>
    )
}

export default React.memo(EmptyCard)
