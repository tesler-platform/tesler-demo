package io.demo.entity;

import io.demo.entity.enums.ClientImportance;
import io.demo.entity.enums.ClientStatus;
import io.demo.entity.enums.FieldOfActivity;
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

	@ElementCollection(targetClass = FieldOfActivity.class)
	@CollectionTable(name = "FIELD_OF_ACTIVITY", joinColumns = @JoinColumn(name = "CLIENT_ID"))
	@Column(name = "VALUE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<FieldOfActivity> fieldOfActivities;

	@OneToMany(mappedBy = "client")
	private Set<Contact> contacts;

	@Enumerated(value = EnumType.STRING)
	private ClientImportance importance;

	@Enumerated(value = EnumType.STRING)
	private ClientStatus status;
}
