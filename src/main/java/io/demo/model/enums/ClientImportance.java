package io.demo.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;

@Getter
@AllArgsConstructor
public enum ClientImportance {
	High("High", "#FFFFFF"),
	Medium("Medium", "#FFFFFF"),
	Low("Low", "#FFFFFF");

	@JsonValue
	private final String importance;
	private final String color;

	@JsonCreator
	private ClientImportance forValue(String value) {
		return EnumUtils.getEnum(ClientImportance.class, value.toUpperCase());
	}

}
