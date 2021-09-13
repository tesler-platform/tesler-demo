package io.demo.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingStatus {
	InProgress("In progress"),
	NotStarted("Not started"),
	Completed("Completed"),
	Error("Error");

	@JsonValue
	private final String value;
}
