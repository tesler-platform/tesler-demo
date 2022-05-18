package io.demo.entity;

import io.demo.entity.enums.Product;
import io.demo.entity.enums.SaleStatus;
import io.tesler.model.core.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SALE")
@Getter
@Setter
@NoArgsConstructor
public class Sale extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@Column
	@Enumerated(EnumType.STRING)
	private Product product;

	@Column
	@Enumerated(EnumType.STRING)
	private SaleStatus status;


	private Long sum;


}
