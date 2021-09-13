package io.demo.entity;

import io.demo.entity.enums.MeetingStatus;
import io.tesler.model.core.entity.BaseEntity;
import io.tesler.model.core.entity.User;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "MEETING")
@Getter
@Setter
@NoArgsConstructor
public class Meeting extends BaseEntity {

	private String agenda;

	private Date startDateTime;

	private Date endDateTime;

	@Enumerated(EnumType.STRING)
	private MeetingStatus status = MeetingStatus.NotStarted;

	private String address;

	private String notes;

	private String result;

	@ManyToOne
	@JoinColumn(name = "RESPONSIBLE_ID")
	private User responsible;

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	private Contact contact;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

}
