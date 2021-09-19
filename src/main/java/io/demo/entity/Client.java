package io.demo.entity;

import io.demo.entity.enums.ClientEditStep;
import io.demo.entity.enums.ClientImportance;
import io.demo.entity.enums.ClientStatus;
import io.demo.entity.enums.FieldOfActivity;
import io.tesler.model.core.entity.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client extends BaseEntity {

	private String fullName;

	private String address;

	@ElementCollection(targetClass = FieldOfActivity.class)
	@CollectionTable(name = "FIELD_OF_ACTIVITY", joinColumns = @JoinColumn(name = "CLIENT_ID"))
	@Column(name = "VALUE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<FieldOfActivity> fieldOfActivities = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private Set<Contact> contacts = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private Set<Meeting> meetings = new HashSet<>();

	@Enumerated(value = EnumType.STRING)
	private ClientImportance importance = ClientImportance.Low;

	@Enumerated(value = EnumType.STRING)
	private ClientStatus status = ClientStatus.New;

	@Enumerated(value = EnumType.STRING)
	private ClientEditStep editStep = ClientEditStep.FillGeneralInformation;

	private String breif;

	private String breifId;

}
