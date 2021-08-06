import React from 'react'
import { ViewNavigation } from '../ViewNavigation/ViewNavigation'
import UserMenu from './components/UserMenu/UserMenu'
import styles from './AppBar.module.css'

export const AppBar: React.FC = () => {
    return <header className={styles.container}>
        <ViewNavigation />
        <UserMenu/>
    </header>
}

export default React.memo(AppBar)
