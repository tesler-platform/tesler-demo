package io.demo.dto;

import io.demo.entity.Client;
import io.demo.entity.enums.FieldOfActivity;
import io.tesler.core.dto.multivalue.MultivalueField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientWriteDTO extends ClientAbstractDTO {

	private MultivalueField fieldOfActivity;

	public ClientWriteDTO(Client client) {
		super(client);
		this.fieldOfActivity = client.getFieldOfActivities()
				.stream()
				.collect(MultivalueField.toMultivalueField(
						Enum::name,
						FieldOfActivity::getValue
				));
	}

}
