package io.demo.service;

import io.demo.dto.ClientReadDTO;
import io.demo.dto.ClientReadDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientPickListMeta extends FieldMetaBuilder<ClientReadDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ClientReadDTO_.id,
				ClientReadDTO_.fullName
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}

}
