package io.demo.response.client.dto;

import io.demo.model.Client;
import io.demo.model.FieldOfActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

import static io.demo.dictionary.DictionaryType.FIELD_OF_ACTIVITY;

@Getter
@Setter
@NoArgsConstructor
public class ClientReadDTO extends ClientAbstractDTO {

	private String address;
	private String fieldOfActivity;

	public ClientReadDTO(Client client) {
		super(client);
		this.address = String.join(", ",
				client.getStreet(),
				client.getBuilding(),
				client.getCity()
		);
		this.fieldOfActivity = client.getFieldOfActivities()
				.stream()
				.map(FieldOfActivity::getValue)
				.map(FIELD_OF_ACTIVITY::lookupValue)
				.collect(Collectors.joining(", "));

	}
}
