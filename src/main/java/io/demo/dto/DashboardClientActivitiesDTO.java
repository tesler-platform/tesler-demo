package io.demo.dto;

import io.demo.entity.Client;
import io.tesler.api.data.dto.DataResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DashboardClientActivitiesDTO extends DataResponseDTO {

	private String clientName;

	private Long numberOfOpenActivities;

	public DashboardClientActivitiesDTO(Client client) {
		this.id = client.getId().toString();
		this.clientName = client.getFullName();
	}

}
