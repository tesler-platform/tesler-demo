package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.dto.ContactDTO_;
import io.demo.entity.Client;
import io.demo.entity.Contact;
import io.demo.entity.Contact_;
import io.demo.repository.ClientRepository;
import io.demo.repository.ContactRepository;
import io.demo.dto.ContactDTO;
import io.tesler.api.data.dto.AssociateDTO;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.AssociateResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.ActionScope;
import io.tesler.core.service.action.Actions;
import io.tesler.model.core.entity.BaseEntity_;
import java.util.Collections;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ClientContactService extends VersionAwareResponseService<ContactDTO, Contact> {

	private final ContactRepository contactRepository;

	private final ClientRepository clientRepository;

	public ClientContactService(ContactRepository contactRepository, ClientRepository clientRepository) {
		super(ContactDTO.class, Contact.class, null, ClientContactMeta.class);
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
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						"/screen/client/view/clienteditcreatecontact/"
								+ TeslerRestController.clientEdit + "/"
								+ client.getId() + "/"
								+ TeslerRestController.contactEdit + "/"
								+ entity.getId()
				));
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
	protected AssociateResultDTO doAssociate(List<AssociateDTO> data, BusinessComponent bc) {
		return new AssociateResultDTO(Collections.emptyList())
				.setAction(PostAction.refreshBc(bc));
	}

	@Override
	public Actions<ContactDTO> getActions() {
		return Actions.<ContactDTO>builder()
				.create()
				.text("Add contact")
				.add()
				.associate()
				.text("Add Existing")
				.add()
				.newAction()
				.action("save_and_go_to_client_edit_contacts", "save")
				.invoker((bc, dto) -> new ActionResultDTO<ContactDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/client/view/clienteditcontacts/"
										+ TeslerRestController.clientEdit + "/"
										+ bc.getParentIdAsLong()

						)))
				.add()
				.newAction()
				.action("edit", "Edit")
				.invoker((bc, dto) -> new ActionResultDTO<ContactDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/client/view/clienteditcreatecontact/"
										+ TeslerRestController.clientEdit + "/"
										+ bc.getParentIdAsLong() + "/"
										+ TeslerRestController.contactEdit + "/"
										+ bc.getId()

						)))
				.add()
				.newAction()
				.action("cancel", "Cancel")
				.scope(ActionScope.BC)
				.withoutAutoSaveBefore()
				.invoker((bc, dto) -> new ActionResultDTO<ContactDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/client/view/clienteditcontacts/"
										+ TeslerRestController.clientEdit + "/"
										+ bc.getParentIdAsLong()

						)))
				.add()
				.build();
	}

}
