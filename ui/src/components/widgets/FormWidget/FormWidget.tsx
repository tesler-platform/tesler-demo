import React, { useMemo } from 'react'
import { Form, Row, Col } from 'antd'
import styles from './FormWidget.module.css'
import cn from 'classnames'
import { WidgetFormMeta, WidgetFormField, WidgetField } from '@tesler-ui/core/interfaces/widget'
import { useBcProps, usePendingProps, useRowMetaProps } from '../../../hooks'
import { Field, TemplatedTitle, useFlatFormFields } from '@tesler-ui/core'
import { FieldType } from '@tesler-ui/core/interfaces/view'

interface FormWidgetProps {
    meta: WidgetFormMeta
}

/**
 *
 * @param props
 * @category Widgets
 */
export const FormWidget = ({ meta }: FormWidgetProps) => {
    const { bcName, name } = meta
    const { cursor } = useBcProps({ bcName })
    const { rowMetaFields: fields, rowMetaErrors: metaErrors } = useRowMetaProps({ bcName, includeSelf: true })
    const { currentPendingValidationFails: missingFields } = usePendingProps({ bcName })

    const allFlattenWidgetFields = useFlatFormFields<WidgetFormField>(meta.fields)

    const { hiddenKeys, flattenWidgetFields } = useMemo(() => {
        const hiddenKeys: string[] = []

        const flattenWidgetFields = allFlattenWidgetFields.filter(item => {
            const isHidden = item.type === FieldType.hidden || item.hidden
            if (isHidden) {
                hiddenKeys.push(item.key)
            }
            return !isHidden
        })

        return { hiddenKeys, flattenWidgetFields }
    }, [allFlattenWidgetFields])

    const memoizedFields = React.useMemo(() => {
        return (
            <Row gutter={24}>
                {meta.options?.layout?.rows.map((row, index) => {
                    return (
                        <Row key={index}>
                            {row.cols
                                .filter(field => {
                                    const fieldMeta = fields?.find(item => item.key === field.fieldKey)
                                    return fieldMeta ? !fieldMeta.hidden : true
                                })
                                .filter(col => !hiddenKeys.includes(col.fieldKey))
                                .map((col, colIndex) => {
                                    const field = flattenWidgetFields.find(item => item.key === col.fieldKey)
                                    const fieldKey = field?.key as string
                                    const fieldLabel = field?.label as string
                                    const disabled = fields?.find(item => item.key === fieldKey && item.disabled)
                                    const error = (!disabled && missingFields?.[fieldKey]) || metaErrors?.[fieldKey]
                                    return (
                                        <Col
                                            key={colIndex}
                                            span={col.span}
                                            className={cn({ [styles.colWrapper]: row.cols.length > 1 || col.span !== 24 })}
                                        >
                                            <Form.Item
                                                label={
                                                    field?.type === 'checkbox' ? null : (
                                                        <TemplatedTitle widgetName={meta.name} title={fieldLabel} />
                                                    )
                                                }
                                                validateStatus={error ? 'error' : undefined}
                                                help={error}
                                            >
                                                <Field
                                                    bcName={bcName}
                                                    cursor={cursor as string}
                                                    widgetName={name}
                                                    widgetFieldMeta={field as WidgetField}
                                                    disableHoverError={true}
                                                />
                                            </Form.Item>
                                        </Col>
                                    )
                                })}
                        </Row>
                    )
                })}
            </Row>
        )
    }, [bcName, name, cursor, flattenWidgetFields, missingFields, metaErrors, hiddenKeys, fields, meta])

    return (
        <Form className={styles.formContainer} colon={false} layout="vertical">
            {memoizedFields}
        </Form>
    )
}

/**
 * @category Widgets
 */
export default React.memo(FormWidget)
