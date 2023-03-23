/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useSelector } from 'react-redux'
import { AppState } from '@interfaces/storeSlices'
import { selectorsFactory } from '@selectors/index'
import { RowMetaSelectProps, SimpleSelectProps } from '@interfaces/selectors'

export function useDataProps(props: SimpleSelectProps) {
    const selectDataProps = useMemo(selectorsFactory.dataProps, [])

    return useSelector((state: AppState) => selectDataProps(state, props))
}

export function useRowMetaProps(props: RowMetaSelectProps) {
    const selectRowMetaProps = useMemo(selectorsFactory.rowMetaProps, [])

    return useSelector((state: AppState) => selectRowMetaProps(state, props))
}

export function usePendingProps(props: SimpleSelectProps) {
    const selectPendingProps = useMemo(selectorsFactory.pendingProps, [])

    return useSelector((state: AppState) => selectPendingProps(state, props))
}

export function useBcProps(props: SimpleSelectProps) {
    const selectBcProps = useMemo(selectorsFactory.bcProps, [])

    return useSelector((state: AppState) => selectBcProps(state, props))
}

export function useWidgetProps(widgetName: string) {
    const selectWidgetProps = useMemo(selectorsFactory.widgetProps, [])

    return useSelector((state: AppState) => selectWidgetProps(state, widgetName))
}
