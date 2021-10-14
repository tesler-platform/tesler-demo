package io.demo.service;

import io.demo.dto.ClientReadDTO;
import io.demo.entity.Client;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import org.springframework.stereotype.Service;

@Service
public class ClientPickListService extends VersionAwareResponseService<ClientReadDTO, Client> {

	public ClientPickListService() {
		super(ClientReadDTO.class, Client.class, null, ClientPickListMeta.class);
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
