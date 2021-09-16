package io.demo.dto;

import io.demo.entity.Client;
import io.demo.entity.enums.FieldOfActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ClientReadDTO extends ClientAbstractDTO {

	private String fieldOfActivity;

	public ClientReadDTO(Client client) {
		super(client);
		this.fieldOfActivity = client.getFieldOfActivities()
				.stream()
				.map(FieldOfActivity::getValue)
				.collect(Collectors.joining(", "));

	}

}
