export type ProposalsServiceResponse = ProposalsDataItem[]

export interface ProposalsDataItem {
    value: string

    data: Record<string, any>
}

export interface ProposalsData {
    proposals: ProposalsDataItem[]
}
