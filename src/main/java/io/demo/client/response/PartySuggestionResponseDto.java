package io.demo.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class PartySuggestionResponseDto {

	private List<Suggestion> suggestions;

	@Data
	@Builder
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Suggestion {

		private String value;

		@JsonProperty("unrestricted_value")
		private String unrestrictedValue;

		private SuggestionData data;

		@Data
		@Builder
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class SuggestionData {

			private String kpp;

			private String inn;

			private String ogrn;

		}

	}

}
