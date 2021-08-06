import React from 'react'
import { WidgetMeta } from '@tesler-ui/core/interfaces/widget'
import styles from './Header.module.css'

interface HeaderProps {
    meta: WidgetMeta
}

function Header(props: HeaderProps) {
    const { meta } = props
    const { title } = meta
    return <h1 className={styles.header}>{title}</h1>
}

export default React.memo(Header)
