package io.demo.response.client.data.impl;

import io.demo.model.Client;
import io.demo.model.Contact;
import io.demo.response.client.data.ClientContactService;
import io.demo.response.client.dto.ContactDTO;
import io.demo.response.client.dto.ContactDTO_;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.service.action.Actions;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import io.tesler.model.core.entity.BaseEntity;

import javax.persistence.metamodel.SingularAttribute;

public class ClientContactServiceImpl extends VersionAwareResponseService<ContactDTO, Contact>
		implements ClientContactService {
	public ClientContactServiceImpl(Class<ContactDTO> typeOfDTO, Class<Contact> typeOfEntity,
			SingularAttribute<? super Contact, ? extends BaseEntity> parentSpec,
			Class<? extends FieldMetaBuilder<ContactDTO>> metaBuilder) {
		super(typeOfDTO, typeOfEntity, parentSpec, metaBuilder);
	}

	@Override
	protected CreateResult<ContactDTO> doCreateEntity(Contact entity, BusinessComponent bc) {
		Client client = baseDAO.findById(Client.class, bc.getParentIdAsLong());
		entity.setClient(client);
		baseDAO.save(entity);
		return new CreateResult<>(entityToDto(bc, entity));
	}

	@Override
	protected ActionResultDTO<ContactDTO> doUpdateEntity(Contact entity, ContactDTO data, BusinessComponent bc) {
		if (data.isFieldChanged(ContactDTO_.fullName)) {
			entity.setFullName(data.getFullName());
		}
		if (data.isFieldChanged(ContactDTO_.email)) {
			entity.setEmail(data.getEmail());
		}
		if (data.isFieldChanged(ContactDTO_.phoneNumber)) {
			entity.setPhoneNumber(data.getPhoneNumber());
		}
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<ContactDTO> getActions() {
		return Actions.<ContactDTO>builder()
				.create().text("Add contact").add()
				.build();
	}
}
