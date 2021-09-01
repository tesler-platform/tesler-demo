package io.demo.entity;

import io.tesler.model.core.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CONTACT")
@Getter
@Setter
@NoArgsConstructor
public class Contact extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	private String fullName;

	private String phoneNumber;

	private String email;

}
