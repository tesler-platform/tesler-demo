import React, { useCallback, useState, useEffect } from 'react'
import { Input, Select } from 'antd'
import { ProposalsDataItem, ProposalsData } from '../../interfaces/data'
import styles from './Proposals.module.css'
import cn from 'classnames'
import { ProposalsWidgetField } from '../../interfaces/widget'
import { useDispatch, useSelector } from 'react-redux'
import { $do } from '../../actions/types'
import { AppState } from '../../interfaces/storeSlices'
import { axiosPost, useDebounce } from '@tesler-ui/core'
import { Observable } from 'rxjs/Observable'

export interface ProposalsProps {
    meta: ProposalsWidgetField
    cursor: string
    widgetName: string
    value: string
}

export function Proposals({ meta: fieldMeta, widgetName, cursor, value }: ProposalsProps) {
    const dispatch = useDispatch()
    const [search, setSearch] = useState('')
    const debouncedSearch = useDebounce(search, 500)
    const [options, setOptions] = useState<ProposalsDataItem[]>()
    const bcName = useSelector((store: AppState) => store.view.widgets.find(item => item.name === widgetName)?.bcName)
    const [valid, setValid] = useState<boolean | undefined>()

    const handleChange = (uniqueValue: string) => {
        const selectedOption = options?.find(item => item?.data[fieldMeta.uniqueValueKey] === uniqueValue)!

        setValid(!!selectedOption)
        if (bcName) {
            const proposalsData =
                fieldMeta.proposalsMap && selectedOption?.data ? createDataItemFrom(fieldMeta.proposalsMap, selectedOption.data) : {}

            dispatch(
                $do.changeDataItem({
                    bcName,
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
        return store.view.pendingDataChanges[bcName!]?.[cursor] ?? store.data[bcName!]?.find(item => item.id === cursor)
    })

    const preselectedValue = record?.[fieldMeta.key] as string

    useEffect(() => {
        if (debouncedSearch && fieldMeta.searchParamName) {
            axiosPost<ProposalsData>(
                `${fieldMeta.endpoint}`,
                {
                    [fieldMeta.searchParamName]: debouncedSearch
                },
                { baseURL: '' }
            )
                .map(response => {
                    setOptions(response.proposals)

                    return Observable.empty()
                })
                .subscribe()
        }
    }, [debouncedSearch, fieldMeta.endpoint, fieldMeta.searchParamName])

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

    /**
     * Зависимые поля
     */
    if (!fieldMeta.proposalsMap) {
        return <Input value={value} disabled />
    }

    /**
     * Управляющее поле
     */
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
                    const mainLabel = fieldMeta.optionsView?.mainLabel ? `${fieldMeta.optionsView?.mainLabel}: ` : ''
                    const additionalInfo = createAdditionalInfo(fieldMeta, option)

                    return (
                        <Select.Option
                            key={option.data[fieldMeta.uniqueValueKey]}
                            value={option.data[fieldMeta.uniqueValueKey]}
                            label={option.value}
                            className={styles.option}
                        >
                            <div className={styles.item}>
                                <span key="main">{`${mainLabel}${option.value}`}</span>
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

export default Proposals

function createDataItemFrom(proposalsMap: Record<string, string>, data: ProposalsDataItem['data']) {
    return Object.entries(proposalsMap).reduce((acc: Record<string, any>, [proposalOldKey, proposalNewKey]) => {
        acc[proposalNewKey] = data[proposalOldKey]

        return acc
    }, {})
}

function createAdditionalInfo(fieldMeta: ProposalsWidgetField, option: ProposalsDataItem) {
    return fieldMeta.optionsView?.additionalView?.map(item => {
        const label = item.label ? `${item.label}: ` : ''
        let value = ''

        if (item.fieldKey && fieldMeta.proposalsMap) {
            value = Object.entries(fieldMeta.proposalsMap).reduce((value: string, [keyForDataSearch, fieldKey]) => {
                if (fieldKey === item.fieldKey) {
                    return option.data[keyForDataSearch]
                }

                return value
            }, '')
        }

        if (item.dataKey) {
            value = option.data[item.dataKey]
        }

        return `${label}${value}`
    })
}
