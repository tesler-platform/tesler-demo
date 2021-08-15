package io.demo.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;

@Getter
@AllArgsConstructor
public enum ClientImportance {
	High("High", "#EC3F3F"),
	Medium("Medium", "#FCA546"),
	Low("Low", "#008C3E");

	@JsonValue
	private final String value;

	private final String color;
}
