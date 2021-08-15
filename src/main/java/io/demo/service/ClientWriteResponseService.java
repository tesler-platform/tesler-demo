package io.demo.service;

import io.demo.model.Client;
import io.demo.model.enums.FieldOfActivity;
import io.demo.repository.ClientRepository;
import io.demo.dto.ClientWriteDTO;
import io.demo.dto.ClientWriteDTO_;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientWriteResponseService extends VersionAwareResponseService<ClientWriteDTO, Client> {

	private final ClientRepository clientRepository;

	public ClientWriteResponseService(ClientRepository clientRepository) {
		super(ClientWriteDTO.class, Client.class, null, ClientWriteFieldMetaBuilder.class);
		this.clientRepository = clientRepository;
	}

	@Override
	protected CreateResult<ClientWriteDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		clientRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity));
	}

	@Override
	protected ActionResultDTO<ClientWriteDTO> doUpdateEntity(Client entity, ClientWriteDTO data, BusinessComponent bc) {
		if (data.isFieldChanged(ClientWriteDTO_.fullName)) {
			entity.setFullName(data.getFullName());
		}
		if (data.isFieldChanged(ClientWriteDTO_.description)) {
			entity.setDescription(data.getDescription());
		}
		if (data.isFieldChanged(ClientWriteDTO_.fieldOfActivity)) {
			entity.setFieldOfActivities(
					data.getFieldOfActivity().getValues()
							.stream()
							.map(v -> FieldOfActivity.getByValue(v.getValue()))
							.collect(Collectors.toSet()));
		}
		if (data.isFieldChanged(ClientWriteDTO_.importance)) {
			entity.setImportance(data.getImportance());
		}
		if (data.isFieldChanged(ClientWriteDTO_.status)) {
			entity.setStatus(data.getStatus());
		}
		if (data.isFieldChanged(ClientWriteDTO_.building)) {
			entity.setBuilding(data.getBuilding());
		}
		if (data.isFieldChanged(ClientWriteDTO_.street)) {
			entity.setStreet(data.getStreet());
		}
		if (data.isFieldChanged(ClientWriteDTO_.city)) {
			entity.setCity(data.getCity());
		}
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<ClientWriteDTO> getActions() {
		return Actions.<ClientWriteDTO>builder()
				.save().add()
				.build();
	}

}
