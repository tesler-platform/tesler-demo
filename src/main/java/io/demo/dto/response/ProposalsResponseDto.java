package io.demo.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProposalsResponseDto {

	private List<Proposal> proposals;

	@Data
	@Builder
	@AllArgsConstructor
	public static class Proposal {

		private String value;

		private ProposalData data;

		@Data
		@Builder
		@AllArgsConstructor
		public static class ProposalData {

			private String inn;

			private String ogrn;

			private String kpp;

		}

	}

}
