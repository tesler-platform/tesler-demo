package io.demo.service;

import io.demo.dto.ResponsibleDTO;
import io.demo.dto.ResponsibleDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ResponsiblePickListMeta extends FieldMetaBuilder<ResponsibleDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ResponsibleDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ResponsibleDTO_.name
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ResponsibleDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}

}
