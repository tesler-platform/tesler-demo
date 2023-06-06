package io.demo.service;

import io.demo.dto.ContactDTO;
import io.demo.entity.Contact;
import io.demo.entity.Contact_;
import io.demo.entity.Meeting;
import io.demo.repository.MeetingRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.model.core.entity.BaseEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ContactPickListService extends VersionAwareResponseService<ContactDTO, Contact> {

	private final MeetingRepository meetingRepository;

	public ContactPickListService(MeetingRepository meetingRepository) {
		super(ContactDTO.class, Contact.class, null, ContactPickListMeta.class);
		this.meetingRepository = meetingRepository;
	}

	@Override
	protected Specification<Contact> getParentSpecification(BusinessComponent bc) {
		Meeting meeting = meetingRepository.getById(bc.getParentIdAsLong());
		return (root, cq, cb) -> cb.and(
				super.getParentSpecification(bc).toPredicate(root, cq, cb),
				cb.equal(
						root.get(Contact_.client).get(BaseEntity_.id),
						meeting.getClient() != null ? meeting.getClient().getId() : null
				)
		);
	}

	@Override
	protected CreateResult<ContactDTO> doCreateEntity(Contact entity, BusinessComponent bc) {
		return null;
	}

	@Override
	protected ActionResultDTO<ContactDTO> doUpdateEntity(Contact entity, ContactDTO data,
			BusinessComponent bc) {
		return null;
	}


}
