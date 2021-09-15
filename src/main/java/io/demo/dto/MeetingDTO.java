package io.demo.dto;

import static java.util.Optional.ofNullable;

import io.demo.entity.Client;
import io.demo.entity.Contact;
import io.demo.entity.Meeting;
import io.demo.entity.enums.MeetingStatus;
import io.tesler.api.data.dto.DataResponseDTO;
import io.tesler.model.core.entity.BaseEntity;
import io.tesler.model.core.entity.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDTO extends DataResponseDTO {

	private String agenda;

	private Date startDateTime;

	private Date endDateTime;

	private String period;

	private MeetingStatus status;

	private String address;

	private String notes;

	private String result;

	private Long responsibleId;

	private String responsibleName;

	private String clientName;

	private Long clientId;

	private Long contactId;

	private String contactName;

	public MeetingDTO(Meeting meeting) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");

		this.id = meeting.getId().toString();
		this.agenda = meeting.getAgenda();
		this.startDateTime = meeting.getStartDateTime();
		this.endDateTime = meeting.getEndDateTime();
		this.period = ofNullable(meeting.getStartDateTime()).map(simpleDateFormat::format).orElse("")
				+ " - " + ofNullable(meeting.getEndDateTime()).map(simpleDateFormat::format).orElse("");
		this.address = meeting.getAddress();
		this.status = meeting.getStatus();
		this.notes = meeting.getNotes();
		this.result = meeting.getResult();
		this.responsibleName = ofNullable(meeting.getResponsible()).map(User::getFullName).orElse(null);
		this.responsibleId = ofNullable(meeting.getResponsible()).map(User::getId).orElse(null);
		this.clientName = ofNullable(meeting.getClient()).map(Client::getFullName).orElse(null);
		this.clientId = ofNullable(meeting.getClient()).map(BaseEntity::getId).orElse(null);
		this.contactId = ofNullable(meeting.getContact()).map(BaseEntity::getId).orElse(null);
		this.contactName = ofNullable(meeting.getContact()).map(Contact::getFullName).orElse(null);
	}

}
