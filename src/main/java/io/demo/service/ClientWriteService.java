package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.entity.Client;
import io.demo.entity.enums.ClientEditStep;
import io.demo.entity.enums.FieldOfActivity;
import io.demo.repository.ClientRepository;
import io.demo.dto.ClientWriteDTO;
import io.demo.dto.ClientWriteDTO_;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.ActionScope;
import io.tesler.core.service.action.Actions;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientWriteService extends VersionAwareResponseService<ClientWriteDTO, Client> {

	private final ClientRepository clientRepository;

	public ClientWriteService(ClientRepository clientRepository) {
		super(ClientWriteDTO.class, Client.class, null, ClientWriteMeta.class);
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
		if (data.isFieldChanged(ClientWriteDTO_.address)) {
			entity.setAddress(data.getAddress());
		}
		if (data.isFieldChanged(ClientWriteDTO_.breif)) {
			entity.setBreif(data.getBreif());
		}
		if (data.isFieldChanged(ClientWriteDTO_.breifId)) {
			entity.setBreifId(data.getBreifId());
		}

		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<ClientWriteDTO> getActions() {
		return Actions.<ClientWriteDTO>builder()
				.save().add()
				.newAction()
				.scope(ActionScope.RECORD)
				.action("next", "Save and Continue")
				.invoker((bc, dto) -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					ClientEditStep nextStep = ClientEditStep.getNextEditStep(client).get();
					client.setEditStep(nextStep);
					clientRepository.save(client);
					return new ActionResultDTO<ClientWriteDTO>().setAction(
							PostAction.drillDown(
									DrillDownType.INNER,
									nextStep.getEditView() + TeslerRestController.clientEdit + "/" + bc.getId()
							));
				})
				.available(bc -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					return ClientEditStep.getNextEditStep(client).isPresent();
				})
				.add()
				.newAction()
				.scope(ActionScope.RECORD)
				.action("finish", "Save and Close")
				.invoker((bc, dto) -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					ClientEditStep firstStep = Arrays.stream(ClientEditStep.values()).findFirst().get();
					client.setEditStep(firstStep);
					clientRepository.save(client);
					return new ActionResultDTO<ClientWriteDTO>().setAction(
							PostAction.drillDown(
									DrillDownType.INNER,
									"/screen/client"
							));
				})
				.available(bc -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					return !ClientEditStep.getNextEditStep(client).isPresent();
				})
				.add()
				.action("previous", "Back")
				.scope(ActionScope.RECORD)
				.invoker((bc, dto) -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					ClientEditStep previousStep = ClientEditStep.getPreviousEditStep(client).get();
					client.setEditStep(previousStep);
					clientRepository.save(client);
					return new ActionResultDTO<ClientWriteDTO>().setAction(
							PostAction.drillDown(
									DrillDownType.INNER,
									previousStep.getEditView() + TeslerRestController.clientEdit + "/" + bc.getId()
							));
				})
				.available(bc -> {
					Client client = clientRepository.getById(bc.getIdAsLong());
					return ClientEditStep.getPreviousEditStep(client).isPresent();
				})
				.add()
				.action("cancel", "Cancel")
				.scope(ActionScope.BC)
				.withoutAutoSaveBefore()
				.invoker((bc, dto) -> new ActionResultDTO<ClientWriteDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/client"
						)))
				.add()
				.build();
	}



}
