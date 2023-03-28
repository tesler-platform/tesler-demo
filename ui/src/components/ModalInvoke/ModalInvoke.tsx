import React from 'react'
import { ModalInvoke as TeslerModalInvoke } from '@teslerComponents'
import styles from './ModalInvoke.module.css'

function ModalInvoke() {
    return <TeslerModalInvoke className={styles.overwrite} />
}

export default React.memo(ModalInvoke)
