package io.demo.field.meta;

import io.tesler.core.ui.field.TeslerWidgetField;
import io.tesler.core.ui.model.json.field.FieldMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TeslerWidgetField({"multipleSelect"})
public class EmptyFieldMeta extends FieldMeta.FieldMetaBase {

}