package io.demo.response.client.data;

import io.demo.model.Client;
import io.demo.model.FieldOfActivity;
import io.demo.response.client.data.ClientWriteResponseService;
import io.demo.response.client.dto.ClientWriteDTO;
import io.demo.response.client.dto.ClientWriteDTO_;
import io.demo.response.client.fieldmeta.ClientWriteFieldMetaBuilder;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.multivalue.MultivalueField;
import io.tesler.core.dto.multivalue.MultivalueFieldSingleValue;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.demo.dictionary.DictionaryType.FIELD_OF_ACTIVITY;

@Service
public class ClientWriteResponseService extends VersionAwareResponseService<ClientWriteDTO, Client> {
	public ClientWriteResponseService() {
		super(ClientWriteDTO.class, Client.class, null, ClientWriteFieldMetaBuilder.class);
	}

	@Override
	protected CreateResult<ClientWriteDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		baseDAO.save(entity);
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
			List<String> selected = getValuesDelta(data, entity);
			selected.forEach(selectedRegionName -> {
				FieldOfActivity fov = new FieldOfActivity();
				fov.setValue(FIELD_OF_ACTIVITY.lookupName(selectedRegionName));
				fov.setClient(entity);
				baseDAO.save(fov);
			});
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
	protected ClientWriteDTO entityToDto(BusinessComponent bc, Client entity) {
		ClientWriteDTO dto = super.entityToDto(bc, entity);
		dto.setFieldOfActivity(
				entity.getFieldOfActivities()
						.stream()
						.collect(MultivalueField.toMultivalueField(
								fov -> fov.getId().toString(),
								fov -> FIELD_OF_ACTIVITY.lookupValue(fov.getValue())

						))
		);
		return dto;
	}

	@Override
	public Actions<ClientWriteDTO> getActions() {
		return Actions.<ClientWriteDTO>builder()
				.save().add()
				.build();
	}

	private List<String> getValuesDelta(ClientWriteDTO clientDto, Client client) {
		List<String> selected = clientDto.getFieldOfActivity().getValues().stream()
				.map(MultivalueFieldSingleValue::getValue)
				.distinct()
				.collect(Collectors.toList());

		Set<FieldOfActivity> currentValues = client.getFieldOfActivities();
		currentValues.removeIf(fieldOfActivity -> !selected.contains(FIELD_OF_ACTIVITY.lookupValue(fieldOfActivity.getValue())));
		currentValues.stream().distinct()
				.map(exist -> FIELD_OF_ACTIVITY.lookupValue(exist.getValue()))
				.filter(selected::contains)
				.forEach(selected::remove);
		return selected;
	}
}
