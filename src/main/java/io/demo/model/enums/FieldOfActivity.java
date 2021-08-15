package io.demo.model.enums;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.annotation.JsonValue;

@Getter
@RequiredArgsConstructor
public enum FieldOfActivity {
	IT("IT"),
	Media("Media"),
	Finance("Finance"),
	Medicine("Medicine"),
	Manufacturing("Manufacturing"),
	Education("Education");

	@JsonValue
	private final String value;

	public static FieldOfActivity getByValue(String value) {
		return Arrays.stream(FieldOfActivity.values()).filter(en -> en.getValue().equals(value)).findFirst().orElse(null);
	}
}
