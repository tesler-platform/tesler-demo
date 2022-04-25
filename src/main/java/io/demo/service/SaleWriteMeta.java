package io.demo.service;

import io.demo.dto.SaleDTO;
import io.demo.dto.SaleDTO_;
import io.demo.entity.enums.Product;
import io.demo.entity.enums.SaleStatus;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class SaleWriteMeta extends FieldMetaBuilder<SaleDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<SaleDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {

		fields.setEnabled(
				SaleDTO_.clientName,
				SaleDTO_.clientId,
				SaleDTO_.product,
				SaleDTO_.status,
				SaleDTO_.sum
		);

		fields.setRequired(
				SaleDTO_.clientName,
				SaleDTO_.clientId,
				SaleDTO_.product,
				SaleDTO_.status,
				SaleDTO_.sum
		);

		fields.setEnumValues(SaleDTO_.product, Product.values());
		fields.setEnumValues(SaleDTO_.status, SaleStatus.values());

	}


	@Override
	public void buildIndependentMeta(FieldsMeta<SaleDTO> fields, InnerBcDescription bcDescription, Long parentId) {
	}

}
