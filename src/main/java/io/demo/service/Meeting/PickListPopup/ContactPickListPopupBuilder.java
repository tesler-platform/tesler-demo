package io.demo.service.Meeting.PickListPopup;

import io.demo.dto.ContactDTO;
import io.demo.dto.ContactDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class ContactPickListPopupBuilder extends FieldMetaBuilder<ContactDTO> {


	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<ContactDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				ContactDTO_.fullName
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<ContactDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}

}
