package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.dto.ClientReadDTO;
import io.demo.dto.ClientReadDTO_;
import io.demo.dto.ClientWriteDTO_;
import io.demo.entity.enums.ClientImportance;
import io.demo.entity.enums.ClientStatus;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientReadMeta extends FieldMetaBuilder<ClientReadDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setDrilldown(
				ClientReadDTO_.fullName,
				DrillDownType.INNER,
				"/screen/client/view/clientview/" + TeslerRestController.client + "/" + id
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ClientReadDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {
		fields.enableFilter(ClientReadDTO_.fullName);
		fields.enableFilter(ClientReadDTO_.address);
		fields.enableFilter(ClientWriteDTO_.importance);
		fields.setEnumFilterValues(fields, ClientWriteDTO_.importance, ClientImportance.values());
		fields.enableFilter(ClientWriteDTO_.status);
		fields.setEnumFilterValues(fields, ClientWriteDTO_.status, ClientStatus.values());
	}

}
