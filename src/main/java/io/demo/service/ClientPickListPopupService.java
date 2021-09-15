package io.demo.service;

import io.demo.dto.ClientReadDTO;
import io.demo.entity.Client;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import org.springframework.stereotype.Service;

@Service
public class ClientPickListPopupService extends VersionAwareResponseService<ClientReadDTO, Client> {

	public ClientPickListPopupService() {
		super(ClientReadDTO.class, Client.class, null, ClientPickListPopupBuilder.class);
	}

	@Override
	protected CreateResult<ClientReadDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		return null;
	}

	@Override
	protected ActionResultDTO<ClientReadDTO> doUpdateEntity(Client entity, ClientReadDTO data,
			BusinessComponent bc) {
		return null;
	}


}
