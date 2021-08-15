package io.demo.service.data;

import io.demo.model.Client;
import io.demo.model.Contact;
import io.demo.model.Contact_;
import io.demo.repository.ClientRepository;
import io.demo.repository.ContactRepository;
import io.demo.service.dto.ContactDTO;
import io.demo.service.dto.ContactDTO_;
import io.demo.service.fieldmeta.ContactFieldMetaBuilder;
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

	private final ContactRepository contactRepository;
	private final ClientRepository clientRepository;

	public ClientContactService(ContactRepository contactRepository, ClientRepository clientRepository) {
		super(ContactDTO.class, Contact.class, null, ContactFieldMetaBuilder.class);
		this.contactRepository = contactRepository;
		this.clientRepository = clientRepository;
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
		Client client = clientRepository.findById(bc.getParentIdAsLong()).orElse(null);
		entity.setClient(client);
		contactRepository.save(entity);
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
