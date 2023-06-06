package io.demo.service;

import io.demo.conf.tesler.icon.ActionIcon;
import io.demo.controller.TeslerRestController;
import io.demo.entity.Client;
import io.demo.entity.Meeting;
import io.demo.entity.enums.ClientStatus;
import io.demo.repository.ClientRepository;
import io.demo.dto.ClientReadDTO;
import io.demo.repository.MeetingRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.MessageType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.dto.rowmeta.PreAction;
import io.tesler.core.service.action.Actions;
import io.tesler.core.util.session.SessionService;
import org.springframework.stereotype.Service;

@Service
public class ClientReadService extends VersionAwareResponseService<ClientReadDTO, Client> {

	private final ClientRepository clientRepository;

	private final MeetingRepository meetingRepository;

	private final SessionService sessionService;

	public ClientReadService(ClientRepository clientRepository, MeetingRepository meetingRepository,
			SessionService sessionService) {
		super(ClientReadDTO.class, Client.class, null, ClientReadMeta.class);
		this.clientRepository = clientRepository;
		this.meetingRepository = meetingRepository;
		this.sessionService = sessionService;
	}

	@Override
	protected CreateResult<ClientReadDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		clientRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						entity.getEditStep().getEditView() + TeslerRestController.clientEdit + "/" + entity.getId()
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
						Actions.<ClientReadDTO>builder()
								.newAction()
								.action("edit", "Edit")
								.withoutAutoSaveBefore()
								.invoker((bc, data) -> {
									Client client = clientRepository.getById(bc.getIdAsLong());
									return new ActionResultDTO<ClientReadDTO>()
											.setAction(PostAction.drillDown(
													DrillDownType.INNER,
													client.getEditStep().getEditView()
															+ TeslerRestController.clientEdit + "/"
															+ bc.getId()
											));
								})
								.add()
								.newAction()
								.action("create_meeting", "Create Meeting")
								.withAutoSaveBefore()
								.invoker((bc, data) -> {
									Client client = clientRepository.getById(bc.getIdAsLong());
									Meeting meeting = meetingRepository.save(new Meeting()
											.setResponsible(sessionService.getSessionUser())
											.setClient(client)
									);
									return new ActionResultDTO<ClientReadDTO>()
											.setAction(PostAction.drillDown(
													DrillDownType.INNER,
													"screen/meeting/view/meetingedit/"
															+ TeslerRestController.meetingEdit + "/"
															+ meeting.getId()
											));
								})
								.available(bc -> false)//TODO>>remove false, after fixing UI error for this drill-down
								.add()
								.newAction()
								.action("deactivate", "Deactivate")
								.withAutoSaveBefore()
								.withPreAction(PreAction.confirm("Are You sure You want to deactivate the client?"))
								.invoker((bc, data) -> {
									Client client = clientRepository.getById(bc.getIdAsLong());
									client.setStatus(ClientStatus.Inactive);
									clientRepository.save(client);
									return new ActionResultDTO<ClientReadDTO>()
											.setAction(PostAction.showMessage(MessageType.INFO, "Client deactivated!"));
								})
								.add()
								.build()
				).withIcon(ActionIcon.MENU, false)
				.build();
	}

	@Override
	public boolean isDeferredCreationSupported(BusinessComponent bc) {
		return false;
	}

}
