package io.demo.service.dto;

import io.demo.model.Client;
import io.demo.model.enums.FieldOfActivity;
import io.tesler.core.dto.multivalue.MultivalueField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientWriteDTO extends ClientAbstractDTO {

	private String city;

	private String street;

	private String building;

	private MultivalueField fieldOfActivity;

	public ClientWriteDTO(Client client) {
		super(client);
		this.city = client.getCity();
		this.street = client.getStreet();
		this.building = client.getBuilding();
		this.fieldOfActivity = client.getFieldOfActivities()
				.stream()
				.collect(MultivalueField.toMultivalueField(
						Enum::name,
						FieldOfActivity::getValue
				));
	}

}
