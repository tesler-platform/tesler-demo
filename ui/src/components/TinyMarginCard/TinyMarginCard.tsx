import React from 'react'
import Card, { CardProps } from '../Card/Card'
import styles from './TinyMarginCard.module.css'

const TinyMarginCard = ({ meta, children }: CardProps) => {
    return (
        <Card className={styles.container} meta={meta}>
            {children}
        </Card>
    )
}

export default React.memo(TinyMarginCard)
