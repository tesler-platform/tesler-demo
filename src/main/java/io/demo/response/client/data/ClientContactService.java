package io.demo.response.client.data;

import io.demo.model.Client;
import io.demo.model.Contact;
import io.demo.model.Contact_;
import io.demo.response.client.data.ClientContactService;
import io.demo.response.client.dto.ContactDTO;
import io.demo.response.client.dto.ContactDTO_;
import io.demo.response.client.fieldmeta.ContactFieldMetaBuilder;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.service.action.Actions;
import io.tesler.model.core.entity.BaseEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ClientContactService extends VersionAwareResponseService<ContactDTO, Contact> {
	public ClientContactService() {
		super(ContactDTO.class, Contact.class, null, ContactFieldMetaBuilder.class);
	}

	@Override
	protected Specification<Contact> getParentSpecification(BusinessComponent bc) {
		return (root, cq, cb) -> cb.and(
				super.getParentSpecification(bc).toPredicate(root, cq, cb),
				cb.equal(root.get(Contact_.client).get(BaseEntity_.id), bc.getParentIdAsLong())
		);

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
				.save().add()
				.build();
	}
}
