import { useDispatch, useSelector } from 'react-redux'
import { useEffect } from 'react'
import { AppState } from '@interfaces/storeSlices'
import { SSO_AUTH } from '@actions/types'

export function useSsoAuth<S extends AppState>(defaultAuthType = SSO_AUTH) {
    const sessionActive = useSelector((state: S) => state.session.active)
    const logoutRequested = useSelector((state: S) => state.session.logout)
    const dispatch = useDispatch()

    useEffect(() => {
        if (!sessionActive && !logoutRequested) {
            dispatch({ type: defaultAuthType })
        }
    }, [sessionActive, logoutRequested, dispatch, defaultAuthType])

    return { sessionActive }
}
