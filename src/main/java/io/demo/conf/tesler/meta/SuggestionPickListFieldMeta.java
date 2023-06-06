package io.demo.conf.tesler.meta;

import io.tesler.core.ui.field.TeslerWidgetField;
import io.tesler.core.ui.model.json.field.FieldMeta.FieldMetaBase;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TeslerWidgetField({"suggestionPickList"})
public class SuggestionPickListFieldMeta extends FieldMetaBase {

	private String popupBcName;

	private Map<String, String> pickMap;

}
