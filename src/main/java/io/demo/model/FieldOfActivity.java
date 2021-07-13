package io.demo.model;

import io.tesler.api.data.dictionary.LOV;
import io.tesler.model.core.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FIELD_OF_ACTIVITY")
@Getter
@Setter
@NoArgsConstructor
public class FieldOfActivity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	private LOV value;

}
