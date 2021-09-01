package io.demo.dto;

import io.demo.entity.Meeting;
import io.demo.entity.enums.MeetingStatus;
import io.tesler.api.data.dto.DataResponseDTO;
import java.util.Date;
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

	private MeetingStatus status = MeetingStatus.Error;

	private String address;

	private String notes;

	private String result;

	private String responsibleName;

	private String clientName;

	private Long client_id;

	private String contactName;

	public MeetingDTO(Meeting meeting) {
		this.id = meeting.getId().toString();
		this.agenda = meeting.getAgenda();
		this.startDateTime = meeting.getStartDateTime();
		this.endDateTime = meeting.getEndDateTime();
		this.address = meeting.getAddress();
		this.status = meeting.getStatus();
		this.notes = meeting.getNotes();
		this.result = meeting.getResult();
		this.responsibleName = meeting.getResponsibleName();
		if(meeting.getClient()!=null) {
			this.clientName = meeting.getClient().getFullName();
			this.client_id = meeting.getClient().getId();
		}
		this.contactName = meeting.getContactName();
	}

}
