package io.demo.response.client.fieldmeta;

import io.demo.crudma.ServiceAssociation;
import io.demo.response.client.dto.ClientReadDTO;
import io.demo.response.client.dto.ClientReadDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientReadFieldMetaBuilder extends FieldMetaBuilder<ClientReadDTO> {
	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setDrilldown(ClientReadDTO_.fullName,
				DrillDownType.INNER,
				String.format(
						"/screen/client/view/clientview/%s/%s",
						ServiceAssociation.client,
						id
				)
		);

	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}
}
