package io.demo.conf.tesler.icon;

import io.tesler.core.service.action.ActionIconSpecifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionIcon implements ActionIconSpecifier {
	MENU("menu");

	String actionIconCode;
}
