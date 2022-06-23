import React from 'react'
import { ModalInvoke as TeslerModalInvoke } from '@tesler-ui/core'
import styles from './ModalInvoke.module.css'

interface ModalInvokeProps {}

function ModalInvoke(props: ModalInvokeProps) {
    return <TeslerModalInvoke className={styles.overwrite} />
}

export default React.memo(ModalInvoke)
