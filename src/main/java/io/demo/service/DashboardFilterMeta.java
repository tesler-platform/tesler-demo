package io.demo.service;

import io.demo.dto.DashboardFilterDTO;
import io.demo.dto.DashboardFilterDTO_;
import io.demo.entity.enums.FieldOfActivity;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class DashboardFilterMeta extends FieldMetaBuilder<DashboardFilterDTO> {


	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<DashboardFilterDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<DashboardFilterDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {
		fields.setEnabled(
				DashboardFilterDTO_.fieldOfActivity
		);
		fields.setDictionaryTypeWithCustomValues(
				DashboardFilterDTO_.fieldOfActivity,
				Arrays.stream(FieldOfActivity.values())
						.map(FieldOfActivity::getValue)
						.toArray(String[]::new)
		);
		fields.setForceActive(DashboardFilterDTO_.fieldOfActivity);
	}

}
