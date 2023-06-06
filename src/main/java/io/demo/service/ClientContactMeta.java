package io.demo.service;

import io.demo.dto.ContactDTO;
import io.demo.dto.ContactDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientContactMeta extends FieldMetaBuilder<ContactDTO>  {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ContactDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ContactDTO_.fullName,
				ContactDTO_.email,
				ContactDTO_.phoneNumber
		);
		fields.setRequired(
				ContactDTO_.fullName,
				ContactDTO_.email,
				ContactDTO_.phoneNumber
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ContactDTO> fields, InnerBcDescription bcDescription, Long parentId) {

	}

}
