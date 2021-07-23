import React from 'react'
import { Button } from 'antd'
import cn from 'classnames'
import styles from './OperationButton.module.css'
import { ButtonProps } from 'antd/lib/button/button'

function OperationButton(props: ButtonProps) {
    const { className, children, onClick, ...rest } = props
    return (
        <Button {...rest} onClick={onClick} className={cn(styles.operation, className)}>
            {children}
        </Button>
    )
}

export default React.memo(OperationButton)
