import React from 'react'
import { WidgetFormMeta } from '@tesler-ui/core/interfaces/widget'
import { FormWidget } from '@tesler-ui/core'
import styles from './Form.module.css'

interface FormProps {
    meta: WidgetFormMeta
}

function Form({ meta }: FormProps) {
    return (
        <div className={styles.formContainer}>
            <FormWidget meta={meta} />
        </div>
    )
}

export default React.memo(Form)
