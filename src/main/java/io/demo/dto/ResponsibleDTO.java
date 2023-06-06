package io.demo.dto;

import io.tesler.api.data.dto.DataResponseDTO;
import io.tesler.model.core.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsibleDTO extends DataResponseDTO {

	private String name;

	public ResponsibleDTO(User user) {
		this.id = user.getId().toString();
		this.name = user.getFullName();
	}

}
