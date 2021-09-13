package io.demo.entity;

import io.demo.entity.enums.MeetingStatus;
import io.tesler.model.core.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEETING")
@Getter
@Setter
@NoArgsConstructor
public class Meeting extends BaseEntity {

	private String agenda;

	private Date startDateTime;

	private Date endDateTime;

	private MeetingStatus status;

	private String address;

	private String notes;

	private String result;

	private String responsibleName;

	private String contactName;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	private void updateStatus() {
		Date date = new Date();
		if (startDateTime != null && endDateTime != null) {
			if (date.before(startDateTime)) {
				this.status = MeetingStatus.NotStarted;
			} else if (date.after(endDateTime)) {
				this.status = MeetingStatus.Completed;
			} else {
				this.status = MeetingStatus.InProgress;
			}
		} else {
			this.status = MeetingStatus.Error;
		}
	}

	public MeetingStatus getStatus() {
		updateStatus();
		return status;
	}

}
