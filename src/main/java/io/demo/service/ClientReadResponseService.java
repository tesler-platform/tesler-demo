package io.demo.service;

import io.demo.common.ActionIcon;
import io.demo.controller.TeslerRestController;
import io.demo.entity.Client;
import io.demo.entity.enums.ClientStatus;
import io.demo.repository.ClientRepository;
import io.demo.dto.ClientReadDTO;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

@Service
public class ClientReadResponseService extends VersionAwareResponseService<ClientReadDTO, Client> {

	private final ClientRepository clientRepository;

	public ClientReadResponseService(ClientRepository clientRepository) {
		super(ClientReadDTO.class, Client.class, null, ClientReadFieldMetaBuilder.class);
		this.clientRepository = clientRepository;
	}

	@Override
	protected CreateResult<ClientReadDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		clientRepository.save(entity);
		entity.setStatus(ClientStatus.New);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						String.format(
								"/screen/client/view/clientedit/%s/%s",
								TeslerRestController.clientEdit,
								entity.getId()
						)
				));
	}

	@Override
	protected ActionResultDTO<ClientReadDTO> doUpdateEntity(Client entity, ClientReadDTO data, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Actions<ClientReadDTO> getActions() {
		return Actions.<ClientReadDTO>builder()
				.create().text("Add").add()
				.addGroup(
						"actions",
						"Actions",
						0,
						Actions.<ClientReadDTO>builder().newAction()
								.action("edit", "Edit")
								.withoutAutoSaveBefore()
								.invoker((bc, data) -> new ActionResultDTO<ClientReadDTO>()
										.setAction(PostAction.drillDown(
												DrillDownType.INNER,
												String.format(
														"/screen/client/view/clientedit/%s/%s",
														TeslerRestController.clientEdit,
														bc.getId()
												)
										)))
								.add()
						.build()
				).withIcon(ActionIcon.MENU, false)
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.invoker((bc, data) -> new ActionResultDTO<ClientReadDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								String.format(
										"/screen/client/view/clientedit/%s/%s",
										TeslerRestController.clientEdit,
										bc.getId()
								)
						)))
				.add()
				.build();
	}

	@Override
	public boolean isDeferredCreationSupported(BusinessComponent bc) {
		return false;
	}

}
