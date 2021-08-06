import React from 'react'
import { Avatar, Dropdown } from 'antd'
import UserMenuContent  from '../UserMenuContent/UserMenuContent'

export const UserMenu: React.FC = () => {
    return <Dropdown overlay={<UserMenuContent/>}><span><Avatar size="small" alt="avatar" icon="user"/></span></Dropdown>
}

export default React.memo(UserMenu)
