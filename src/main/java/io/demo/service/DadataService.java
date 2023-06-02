package io.demo.service;

import io.demo.client.DadataClient;
import io.demo.client.request.PartySuggestionRequestDto;
import io.demo.client.response.PartySuggestionResponseDto;
import io.demo.client.response.PartySuggestionResponseDto.Suggestion;
import io.demo.dto.request.ProposalsRequestDto;
import io.demo.dto.response.ProposalsResponseDto;
import io.demo.dto.response.ProposalsResponseDto.Proposal;
import io.demo.dto.response.ProposalsResponseDto.Proposal.ProposalData;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DadataService {

	private final DadataClient dadataClient;


	public ProposalsResponseDto getProposals(ProposalsRequestDto rqDto) {

		PartySuggestionRequestDto suggestionRq = PartySuggestionRequestDto.builder()
				.query(rqDto.getQuery())
				.count(rqDto.getCount())
				.build();
		PartySuggestionResponseDto partySuggestionResponse = dadataClient.getCompanySuggestion(suggestionRq);
		return ProposalsResponseDto.builder()
				.proposals(
						partySuggestionResponse.getSuggestions()
								.stream()
								.map(this::mapToProposal)
								.collect(Collectors.toList())
				)
				.build();

	}

	private Proposal mapToProposal(Suggestion suggestion) {
		return Proposal.builder()
				.value(suggestion.getValue())
				.data(mapToProposalData(suggestion))
				.build();
	}

	private ProposalData mapToProposalData(Suggestion suggestion) {
		return ProposalData.builder()
				.inn(suggestion.getData().getInn())
				.kpp(suggestion.getData().getKpp())
				.ogrn(suggestion.getData().getOgrn())
				.build();
	}

}
