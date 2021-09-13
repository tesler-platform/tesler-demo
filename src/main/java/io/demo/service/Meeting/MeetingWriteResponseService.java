package io.demo.service.Meeting;

import io.demo.controller.TeslerRestController;
import io.demo.dto.MeetingDTO;
import io.demo.dto.MeetingDTO_;
import io.demo.entity.Meeting;
import io.demo.repository.ClientRepository;
import io.demo.repository.MeetingRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

@Service
public class MeetingWriteResponseService extends VersionAwareResponseService<MeetingDTO, Meeting> {

	private final MeetingRepository meetingRepository;

	private final ClientRepository clientRepository;


	public MeetingWriteResponseService(MeetingRepository meetingRepository, ClientRepository clientRepository) {
		super(MeetingDTO.class, Meeting.class, null, MeetingWriteFieldMetaBuilder.class);
		this.meetingRepository = meetingRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	protected CreateResult<MeetingDTO> doCreateEntity(Meeting entity, BusinessComponent bc) {
		meetingRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/meeting/view/meetingedit/"
						+ TeslerRestController.responsiblePickListPopup
						+ "/" + entity.getId()
		)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/meeting/view/meetingedit/"
						+ TeslerRestController.clientPickListPopup
						+ "/" + entity.getId()
		)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/meeting/view/meetingedit/"
						+ TeslerRestController.contactPickListPopup
						+ "/" + entity.getId()
		))
				;
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
		if (data.isFieldChanged(MeetingDTO_.responsibleName)) {
			entity.setResponsibleName(data.getResponsibleName());
		}
		if (data.isFieldChanged(MeetingDTO_.client_id)) {
			if (data.getClient_id() != null) {
				entity.setClient(clientRepository.getById(data.getClient_id()));
			} else {
				entity.setClient(null);
			}
			entity.setContactName(null);
		}
		if (data.isFieldChanged(MeetingDTO_.contactName)) {
			entity.setContactName(data.getContactName());
		}
		meetingRepository.save(entity);
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<MeetingDTO> getActions() {
		return Actions.<MeetingDTO>builder()
				.save().add()
				.build();
	}

}
