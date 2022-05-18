package io.demo.dto;

import io.tesler.api.data.dto.DataResponseDTO;
import io.tesler.core.dto.multivalue.MultivalueField;
import io.tesler.core.util.filter.SearchParameter;
import io.tesler.core.util.filter.provider.impl.StringValueProvider;
import io.tesler.model.core.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DashboardFilterDTO extends DataResponseDTO {

	@SearchParameter(name = "fieldOfActivities.value", multiFieldKey = StringValueProvider.class)
	private MultivalueField fieldOfActivity;

	public DashboardFilterDTO(User user) {
		this.id = user.getId().toString();
	}

}
