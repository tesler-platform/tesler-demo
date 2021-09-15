package io.demo.service;

import io.demo.dto.ResponsibleDTO;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.model.core.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ResponsiblePickListPopupService extends VersionAwareResponseService<ResponsibleDTO, User> {

	public ResponsiblePickListPopupService() {
		super(ResponsibleDTO.class, User.class, null, ResponsiblePickListPopupBuilder.class);
	}

	@Override
	protected CreateResult<ResponsibleDTO> doCreateEntity(User entity, BusinessComponent bc) {
		return null;
	}

	@Override
	protected ActionResultDTO<ResponsibleDTO> doUpdateEntity(User entity, ResponsibleDTO data,
			BusinessComponent bc) {
		return null;
	}


}
