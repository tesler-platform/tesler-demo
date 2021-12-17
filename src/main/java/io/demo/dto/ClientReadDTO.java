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

	private String drillDown = "СКАЧАТЬ";

	private String drillDownKey = "https://repo1.maven.org/maven2/io/tesler/tesler-core/1.0/tesler-core-1.0.jar";

	public ClientReadDTO(Client client) {
		super(client);
		this.fieldOfActivity = client.getFieldOfActivities()
				.stream()
				.map(FieldOfActivity::getValue)
				.collect(Collectors.joining(", "));

	}

}
