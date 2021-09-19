package io.demo.dto;

import io.demo.entity.Client;
import io.demo.entity.enums.FieldOfActivity;
import io.tesler.core.dto.multivalue.MultivalueField;
import io.tesler.core.util.filter.SearchParameter;
import io.tesler.core.util.filter.provider.impl.StringValueProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientWriteDTO extends ClientAbstractDTO {

	@SearchParameter(name = "fieldOfActivities.value", multiFieldKey = StringValueProvider.class)
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
