package io.demo.service;

import io.demo.dto.DashboardSalesFunnelDTO;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class DashboardSalesFunnelMeta extends FieldMetaBuilder<DashboardSalesFunnelDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<DashboardSalesFunnelDTO> fields,
			InnerBcDescription bcDescription,
			Long id, Long parentId) {

	}

	@Override
	public void buildIndependentMeta(FieldsMeta<DashboardSalesFunnelDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {
	}

}
