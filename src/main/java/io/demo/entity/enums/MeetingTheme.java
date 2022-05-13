package io.demo.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingTheme {
	Daily("Daily"),
	Retrospective("Retrospective"),
	Other("Other");

	@JsonValue
	private final String value;
}
