package io.demo.service.fieldmeta;

import io.demo.model.enums.ClientImportance;
import io.demo.model.enums.FieldOfActivity;
import io.demo.service.dto.ClientWriteDTO;
import io.demo.service.dto.ClientWriteDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class ClientWriteFieldMetaBuilder extends FieldMetaBuilder<ClientWriteDTO> {
	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientWriteDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ClientWriteDTO_.fullName,
				ClientWriteDTO_.city,
				ClientWriteDTO_.street,
				ClientWriteDTO_.building,
				ClientWriteDTO_.description,
				ClientWriteDTO_.importance,
				ClientWriteDTO_.fieldOfActivity
		);
		fields.setRequired(ClientWriteDTO_.fullName,
				ClientWriteDTO_.importance,
				ClientWriteDTO_.description,
				ClientWriteDTO_.city,
				ClientWriteDTO_.fieldOfActivity);

		fields.setEnumValues(ClientWriteDTO_.importance, ClientImportance.values());

		fields.setDictionaryTypeWithCustomValues(ClientWriteDTO_.fieldOfActivity,
				Arrays.stream(FieldOfActivity.values()).map(FieldOfActivity::getValue)
						.toArray(String[]::new)
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientWriteDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}
}
