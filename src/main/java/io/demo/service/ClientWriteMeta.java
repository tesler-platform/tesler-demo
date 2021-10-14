package io.demo.service;

import io.demo.dto.ClientWriteDTO_;
import io.demo.entity.enums.ClientEditStep;
import io.demo.entity.enums.ClientImportance;
import io.demo.entity.enums.ClientStatus;
import io.demo.entity.enums.FieldOfActivity;
import io.demo.dto.ClientWriteDTO;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class ClientWriteMeta extends FieldMetaBuilder<ClientWriteDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientWriteDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ClientWriteDTO_.fullName,
				ClientWriteDTO_.address,
				ClientWriteDTO_.importance,
				ClientWriteDTO_.fieldOfActivity,
				ClientWriteDTO_.breifId,
				ClientWriteDTO_.breif,
				ClientWriteDTO_.editStep,
				ClientWriteDTO_.status
		);
		fields.setRequired(
				ClientWriteDTO_.fullName,
				ClientWriteDTO_.importance,
				ClientWriteDTO_.address,
				ClientWriteDTO_.fieldOfActivity,
				ClientWriteDTO_.status
		);

		fields.setEnumValues(ClientWriteDTO_.importance, ClientImportance.values());

		fields.setEnumValues(ClientWriteDTO_.editStep, ClientEditStep.values());

		fields.setEnumValues(ClientWriteDTO_.status, ClientStatus.values());

		fields.setDictionaryTypeWithCustomValues(
				ClientWriteDTO_.fieldOfActivity,
				Arrays.stream(FieldOfActivity.values())
						.map(FieldOfActivity::getValue)
						.toArray(String[]::new)
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientWriteDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {
	}

}
