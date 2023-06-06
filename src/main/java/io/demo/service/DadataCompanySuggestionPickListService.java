package io.demo.service;

import io.demo.client.DadataClient;
import io.demo.client.request.PartySuggestionRequestDto;
import io.demo.client.response.PartySuggestionResponseDto;
import io.demo.client.response.PartySuggestionResponseDto.Suggestion;
import io.demo.dto.CompanySuggestionDTO;
import io.tesler.api.data.ResultPage;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.AbstractCrudmaService;
import io.tesler.core.exception.ClientException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DadataCompanySuggestionPickListService extends AbstractCrudmaService {

	private static final String SEARCH_QUERY_PARAMETER = "query";

	private static final String SEARCH_COUNT_PARAMETER = "_limit";

	private static final String MISSING_QUERY_PARAMETER_EXCEPTION_MESSAGE = "No query parameter provided";

	private final DadataClient dadataClient;

	@Override
	public ResultPage<CompanySuggestionDTO> getAll(BusinessComponent bc) {
		return ResultPage.of(getSuggestion(bc), false);
	}

	@Override
	public long count(BusinessComponent bc) {
		return 20;
	}


	private List<CompanySuggestionDTO> getSuggestion(BusinessComponent bc) {
		String query = getBcParameter(bc, SEARCH_QUERY_PARAMETER)
				.orElseThrow(
						() -> new ClientException(MISSING_QUERY_PARAMETER_EXCEPTION_MESSAGE)
				);
		Integer countParameter = getBcParameter(bc, SEARCH_COUNT_PARAMETER)
				.filter(StringUtils::isNumeric)
				.map(Integer::parseInt)
				.orElse(null);
		PartySuggestionRequestDto suggestionRq = PartySuggestionRequestDto.builder()
				.query(query)
				.count(countParameter)
				.build();
		PartySuggestionResponseDto partySuggestionResponse = dadataClient.getPartySuggestion(suggestionRq);
		return partySuggestionResponse.getSuggestions()
				.stream()
				.map(this::mapToSuggestionDataResponseDTO)
				.collect(Collectors.toList());
	}


	private Optional<String> getBcParameter(BusinessComponent bc, String parameterName) {
		return Optional.ofNullable(bc.getParameters())
				.map(busComp -> busComp.getParameter(parameterName));
	}


	private CompanySuggestionDTO mapToSuggestionDataResponseDTO(Suggestion suggestion) {
		return CompanySuggestionDTO.builder()
				.id(UUID.randomUUID().toString())
				.value(suggestion.getValue())
				.inn(suggestion.getData().getInn())
				.kpp(suggestion.getData().getKpp())
				.ogrn(suggestion.getData().getOgrn())
				.build();
	}

}
