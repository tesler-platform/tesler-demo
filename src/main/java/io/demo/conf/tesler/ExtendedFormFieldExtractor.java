package io.demo.conf.tesler;

import static io.tesler.api.util.i18n.LocalizationFormatter.i18n;

import com.google.common.collect.ImmutableList;
import io.tesler.core.ui.model.BcField;
import io.tesler.core.ui.model.json.field.FieldGroup;
import io.tesler.core.ui.model.json.field.FieldMeta;
import io.tesler.core.util.JsonUtils;
import io.tesler.model.ui.entity.Widget;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtendedFormFieldExtractor extends DemoBaseFieldExtractor {

	@Override
	public Set<BcField> extract(Widget widget) {
		final Set<BcField> widgetFields = new HashSet<>(extractFieldsFromTitle(widget, i18n(widget.getTitle())));
		for (final FieldGroup group : JsonUtils.readValue(FieldGroup[].class, widget.getFields())) {
			if (group.getChildren() != null) {
				for (final FieldMeta field : group.getChildren()) {
					widgetFields.addAll(extract(widget, field));
				}
			}
		}
		return widgetFields;
	}

	@Override
	public List<String> getSupportedTypes() {
		return ImmutableList.of(
				"Form"
		);
	}

	@Override
	public int getPriority() {
		return Integer.MAX_VALUE;
	}

}
