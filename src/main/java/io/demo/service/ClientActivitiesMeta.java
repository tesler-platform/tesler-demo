package io.demo.service;

import io.demo.dto.ClientActivitiesDTO;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientActivitiesMeta extends FieldMetaBuilder<ClientActivitiesDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientActivitiesDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientActivitiesDTO> fields, InnerBcDescription bcDescription, Long parentId) {

	}

}
