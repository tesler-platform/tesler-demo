package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.dto.MeetingDTO;
import io.demo.dto.MeetingDTO_;
import io.demo.entity.Meeting;
import io.demo.repository.ClientRepository;
import io.demo.repository.ContactRepository;
import io.demo.repository.MeetingRepository;
import io.demo.repository.UserRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.ActionScope;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

@Service
public class MeetingWriteService extends VersionAwareResponseService<MeetingDTO, Meeting> {

	private final MeetingRepository meetingRepository;

	private final ClientRepository clientRepository;

	private final ContactRepository contactRepository;

	private final UserRepository userRepository;

	public MeetingWriteService(MeetingRepository meetingRepository, ClientRepository clientRepository,
			ContactRepository contactRepository, UserRepository userRepository) {
		super(MeetingDTO.class, Meeting.class, null, MeetingWriteMeta.class);
		this.meetingRepository = meetingRepository;
		this.clientRepository = clientRepository;
		this.contactRepository = contactRepository;
		this.userRepository = userRepository;
	}

	@Override
	protected CreateResult<MeetingDTO> doCreateEntity(Meeting entity, BusinessComponent bc) {
		meetingRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/meeting/view/meetingedit/"
						+ TeslerRestController.meetingEdit
						+ "/" + entity.getId()
		));
	}

	@Override
	protected ActionResultDTO<MeetingDTO> doUpdateEntity(Meeting entity, MeetingDTO data, BusinessComponent bc) {
		if (data.isFieldChanged(MeetingDTO_.agenda)) {
			entity.setAgenda(data.getAgenda());
		}
		if (data.isFieldChanged(MeetingDTO_.startDateTime)) {
			entity.setStartDateTime(data.getStartDateTime());
		}
		if (data.isFieldChanged(MeetingDTO_.endDateTime)) {
			entity.setEndDateTime(data.getEndDateTime());
		}
		if (data.isFieldChanged(MeetingDTO_.address)) {
			entity.setAddress(data.getAddress());
		}
		if (data.isFieldChanged(MeetingDTO_.notes)) {
			entity.setNotes(data.getNotes());
		}
		if (data.isFieldChanged(MeetingDTO_.result)) {
			entity.setResult(data.getResult());
		}
		if (data.isFieldChanged(MeetingDTO_.responsibleId)) {
			if (data.getResponsibleId() != null) {
				entity.setResponsible(userRepository.getById(data.getResponsibleId()));
			} else {
				entity.setResponsible(null);
			}
		}
		if (data.isFieldChanged(MeetingDTO_.clientId)) {
			if (data.getClientId() != null) {
				entity.setClient(clientRepository.getById(data.getClientId()));
			} else {
				entity.setClient(null);
			}
			entity.setContact(null);
		}
		if (data.isFieldChanged(MeetingDTO_.contactId)) {
			if (data.getContactId() != null) {
				entity.setContact(contactRepository.getById(data.getContactId()));
			} else {
				entity.setContact(null);
			}
		}
		meetingRepository.save(entity);
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<MeetingDTO> getActions() {
		return Actions.<MeetingDTO>builder()
				.newAction()
				.scope(ActionScope.RECORD)
				.withAutoSaveBefore()
				.action("saveAndContinue", "Save")
				.invoker((bc, dto) -> new ActionResultDTO<MeetingDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/meeting/view/meetingview/" + TeslerRestController.meeting + "/" + bc.getId()
						)))
				.add()
				.newAction()
				.action("cancel", "Cancel")
				.scope(ActionScope.BC)
				.withoutAutoSaveBefore()
				.invoker((bc, dto) -> new ActionResultDTO<MeetingDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/meeting/"
						)))
				.add()
				.build();
	}

}
