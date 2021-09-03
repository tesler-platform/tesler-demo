package io.demo.common;

import io.tesler.core.service.action.ActionIconSpecifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionIcon implements ActionIconSpecifier {
	MENU("menu");

	String actionIconCode;
}
