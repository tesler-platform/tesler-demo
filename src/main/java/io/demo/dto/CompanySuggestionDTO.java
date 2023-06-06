package io.demo.dto;

import io.tesler.api.data.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CompanySuggestionDTO extends DataResponseDTO {

	private String value;

	private String inn;

	private String ogrn;

	private String kpp;

	@Builder
	public CompanySuggestionDTO(String id, String value, String inn, String ogrn, String kpp) {
		this.id = id;
		this.value = value;
		this.inn = inn;
		this.ogrn = ogrn;
		this.kpp = kpp;
	}

}
