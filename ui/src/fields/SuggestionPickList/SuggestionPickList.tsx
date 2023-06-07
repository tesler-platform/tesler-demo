import React, { useCallback, useState, useEffect } from 'react'
import { Select } from 'antd'
import { SuggestionPickListDataItem } from '../../interfaces/data'
import styles from './SuggestionPickList.module.css'
import cn from 'classnames'
import { SuggestionPickListField, SuggestionPickListWidgetMeta } from '../../interfaces/widget'
import { shallowEqual, useDispatch, useSelector } from 'react-redux'
import { $do } from '../../actions/types'
import { AppState } from '../../interfaces/storeSlices'
import { buildBcUrl, fetchBcData, useDebounce } from '@tesler-ui/core'
import { Observable } from 'rxjs/Observable'

export interface SuggestionPickListProps {
    meta: SuggestionPickListField
    cursor: string
    widgetName: string
    value: string
}

export function SuggestionPickList({ meta: fieldMeta, widgetName, cursor }: SuggestionPickListProps) {
    const dispatch = useDispatch()
    const [search, setSearch] = useState('')
    const debouncedSearch = useDebounce(search, 500)
    const [options, setOptions] = useState<SuggestionPickListDataItem[]>()
    const widgetBcName = useSelector((store: AppState) => store.view.widgets.find(item => item.name === widgetName)?.bcName)
    const [valid, setValid] = useState<boolean | undefined>()

    const handleChange = (id: string) => {
        const selectedOption = options?.find(item => item?.id === id)!

        setValid(!!selectedOption)
        if (widgetBcName) {
            const proposalsData = fieldMeta.pickMap && selectedOption ? createDataItemFrom(fieldMeta.pickMap, selectedOption) : {}

            dispatch(
                $do.changeDataItem({
                    bcName: widgetBcName,
                    cursor,
                    dataItem: {
                        [fieldMeta.key]: selectedOption?.value,
                        ...proposalsData
                    }
                })
            )
        }
    }

    const record = useSelector((store: AppState) => {
        return store.view.pendingDataChanges[widgetBcName!]?.[cursor] ?? store.data[widgetBcName!]?.find(item => item.id === cursor)
    })

    const preselectedValue = record?.[fieldMeta.key] as string
    const { fieldBcUrl, fieldBc, screenName, fieldWidget } = useSelector((state: AppState) => {
        const fieldBcName = fieldMeta.popupBcName
        const fieldBc = state.screen.bo.bc[fieldBcName]
        const limitBySelfCursor = state.router.bcPath?.includes(`${fieldBcName}/${cursor}`)

        return {
            screenName: state.screen.screenName,
            fieldBcUrl: buildBcUrl(fieldBcName, limitBySelfCursor),
            fieldBc,
            fieldWidget: state.view.widgets.find(widget => widget.bcName === fieldMeta.popupBcName) as SuggestionPickListWidgetMeta
        }
    }, shallowEqual)

    useEffect(() => {
        if (debouncedSearch) {
            const fetchParams: Record<string, any> = {
                query: debouncedSearch,
                _page: fieldBc.page,
                _limit: fieldWidget.limit || fieldBc.limit
            }

            fetchBcData(screenName, fieldBcUrl, fetchParams)
                .map(response => {
                    setOptions(response.data as unknown as SuggestionPickListDataItem[])

                    return Observable.empty()
                })
                .subscribe()
        }
    }, [fieldBc.limit, fieldBc.page, fieldBcUrl, debouncedSearch, fieldMeta.popupBcName, screenName, fieldWidget.limit])

    const handleSearch = (token: string) => {
        setSearch(token)
    }

    const handleValidation = useCallback(() => {
        if (!preselectedValue) {
            setValid(false)
            setSearch('')
        } else {
            setValid(true)
        }
    }, [preselectedValue])

    return (
        <div className={styles.container}>
            <Select
                showSearch
                optionLabelProp="label"
                defaultValue={preselectedValue}
                allowClear
                filterOption={false}
                onSearch={handleSearch}
                className={cn({ [styles.good]: valid === true, [styles.bad]: valid === false })}
                onChange={handleChange}
                onBlur={handleValidation}
                defaultActiveFirstOption={false}
            >
                {options?.map(option => {
                    const additionalInfo = createAdditionalInfo(fieldWidget, option)

                    return (
                        <Select.Option key={option.id} value={option.id} label={option.value} className={styles.option}>
                            <div className={styles.item}>
                                {additionalInfo?.map(text => {
                                    return <span key={text}>{text}</span>
                                })}
                            </div>
                        </Select.Option>
                    )
                })}
            </Select>
            {valid === false && <div className={styles.error}>Select a value from the dropdown list</div>}
        </div>
    )
}

export default SuggestionPickList

function createDataItemFrom(pickMap: Record<string, string>, data: SuggestionPickListDataItem['data']) {
    return Object.entries(pickMap).reduce((acc: Record<string, any>, [proposalNewKey, proposalOldKey]) => {
        acc[proposalNewKey] = data[proposalOldKey]

        return acc
    }, {})
}

function createAdditionalInfo(fieldWidget: SuggestionPickListWidgetMeta, option: SuggestionPickListDataItem) {
    return fieldWidget?.fields?.map(field => {
        const label = field.title ? `${field.title}: ` : ''
        const value = option[field.key]

        return `${label}${value}`
    })
}
