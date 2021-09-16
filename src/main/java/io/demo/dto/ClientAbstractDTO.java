package io.demo.dto;

import io.demo.entity.Client;
import io.demo.entity.enums.ClientEditStep;
import io.demo.entity.enums.ClientImportance;
import io.demo.entity.enums.ClientStatus;
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

	private String address;

	private ClientImportance importance;

	private ClientEditStep editStep;

	private String color;

	private String breif;

	private String breifId;

	ClientAbstractDTO(Client client) {
		this.id = client.getId().toString();
		this.address = client.getAddress();
		this.fullName = client.getFullName();
		this.importance = client.getImportance();
		this.editStep = client.getEditStep();
		this.color = Optional.ofNullable(client.getImportance()).map(ClientImportance::getColor).orElse(null);
		this.breif = client.getBreif();
		this.breifId = client.getBreifId();
	}

}
