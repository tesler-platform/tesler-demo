package io.demo.service;

import io.demo.dto.DashboardClientActivitiesDTO;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class DashboardClientActivitiesMeta extends FieldMetaBuilder<DashboardClientActivitiesDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<DashboardClientActivitiesDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<DashboardClientActivitiesDTO> fields, InnerBcDescription bcDescription, Long parentId) {

	}

}
