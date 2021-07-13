package io.demo.model;

import io.demo.model.enums.ClientImportance;
import io.demo.model.enums.ClientStatus;
import io.tesler.model.core.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client extends BaseEntity {

	private String fullName;

	private String description;

	private String city;

	private String street;

	private String building;

	@OneToMany(mappedBy = "client")
	private Set<FieldOfActivity> fieldOfActivities;

	@OneToMany(mappedBy = "client")
	private Set<Contact> contacts;

	@Enumerated(value = EnumType.STRING)
	private ClientImportance importance;

	@Enumerated(value = EnumType.STRING)
	private ClientStatus status;
}
