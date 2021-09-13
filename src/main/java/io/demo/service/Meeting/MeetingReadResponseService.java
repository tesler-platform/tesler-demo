package io.demo.service.Meeting;

import io.demo.controller.TeslerRestController;
import io.demo.dto.MeetingDTO;
import io.demo.entity.Meeting;
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
public class MeetingReadResponseService extends VersionAwareResponseService<MeetingDTO, Meeting> {

	private final MeetingRepository meetingRepository;

	public MeetingReadResponseService(MeetingRepository meetingRepository) {
		super(MeetingDTO.class, Meeting.class, null, MeetingReadFieldMetaBuilder.class);
		this.meetingRepository = meetingRepository;
	}

	@Override
	protected CreateResult<MeetingDTO> doCreateEntity(Meeting entity, BusinessComponent bc) {
		meetingRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						String.format(
								"/screen/meeting/view/meetingedit/%s/%s",
								TeslerRestController.meetingEdit,
								entity.getId()
						)
				));
	}

	@Override
	protected ActionResultDTO<MeetingDTO> doUpdateEntity(Meeting entity, MeetingDTO data, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Actions<MeetingDTO> getActions() {
		return Actions.<MeetingDTO>builder()
				.create().text("Create Meeting").add()
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.invoker((bc, data) -> new ActionResultDTO<MeetingDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								String.format(
										"/screen/meeting/view/meetingedit/%s/%s",
										TeslerRestController.meetingEdit,
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
