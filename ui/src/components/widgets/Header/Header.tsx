import React, { FunctionComponent } from 'react'
import { WidgetMeta } from '@tesler-ui/core/interfaces/widget'
import styles from './Header.module.css'
import { TemplatedTitle } from '@tesler-ui/core'
import ActionsGroup from '../Info/components/ActionsGroup'

interface HeaderProps {
    meta: WidgetMeta
}

const TitleContainer: FunctionComponent<{ title: string }> = ({ title }) => {
    return <h1 className={styles.header}>{title}</h1>
}

function Header({ meta }: HeaderProps) {
    const { title, name } = meta
    return (
        <>
            <TemplatedTitle title={title} widgetName={name} container={TitleContainer} />
            <ActionsGroup meta={meta} />
        </>
    )
}

export default React.memo(Header)
