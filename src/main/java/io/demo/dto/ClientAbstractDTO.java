package io.demo.dto;

import io.demo.model.Client;
import io.demo.model.enums.ClientImportance;
import io.demo.model.enums.ClientStatus;
import io.tesler.api.data.dto.DataResponseDTO;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class ClientAbstractDTO extends DataResponseDTO {

	private String fullName;

	private String description;

	private ClientImportance importance;

	private ClientStatus status;

	private String color;

	ClientAbstractDTO(Client client) {
		this.id = client.getId().toString();
		this.fullName = client.getFullName();
		this.description = client.getDescription();
		this.importance = client.getImportance();
		this.status = client.getStatus();
		this.color = Optional.ofNullable(client.getImportance()).map(ClientImportance::getColor).orElse(null);
	}
}
