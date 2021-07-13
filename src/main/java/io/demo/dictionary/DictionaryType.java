package io.demo.dictionary;

import io.tesler.api.data.dictionary.IDictionaryType;
import io.tesler.api.data.dictionary.LOV;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import static io.tesler.api.data.dictionary.DictionaryCache.dictionary;

@RequiredArgsConstructor
public enum DictionaryType implements Serializable, IDictionaryType {
	FIELD_OF_ACTIVITY;


	@Override
	public String getName() {
		return name();
	}

	@Override
	public LOV lookupName(String stringValue) {
		return dictionary().lookupName(stringValue, this);
	}

	@Override
	public String lookupValue(LOV lovValue) {
		return dictionary().lookupValue(lovValue, this);
	}
}