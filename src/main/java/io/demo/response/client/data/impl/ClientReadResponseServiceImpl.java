package io.demo.response.client.data.impl;

import io.demo.crudma.ServiceAssociation;
import io.demo.model.Client;
import io.demo.model.enums.ClientStatus;
import io.demo.response.client.data.ClientReadResponseService;
import io.demo.response.client.dto.ClientReadDTO;
import io.demo.response.client.fieldmeta.ClientReadFieldMetaBuilder;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

@Service
public class ClientReadResponseServiceImpl extends VersionAwareResponseService<ClientReadDTO, Client>
		implements ClientReadResponseService {
	public ClientReadResponseServiceImpl() {
		super(ClientReadDTO.class, Client.class, null, ClientReadFieldMetaBuilder.class);
	}

	@Override
	protected CreateResult<ClientReadDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		baseDAO.save(entity);
		entity.setStatus(ClientStatus.New);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						String.format(
								"/screen/client/view/clientedit/%s/%s",
								ServiceAssociation.clientEdit,
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
				.create().text("Create Client").add()
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.invoker((bc, data) -> new ActionResultDTO<ClientReadDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								String.format(
										"/screen/client/view/clientedit/%s/%s",
										ServiceAssociation.clientEdit,
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
