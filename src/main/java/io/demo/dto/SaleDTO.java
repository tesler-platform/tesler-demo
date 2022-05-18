package io.demo.dto;

import io.demo.entity.Sale;
import io.demo.entity.enums.Product;
import io.demo.entity.enums.SaleStatus;
import io.tesler.api.data.dto.DataResponseDTO;
import io.tesler.core.util.filter.SearchParameter;
import io.tesler.core.util.filter.provider.impl.EnumValueProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO extends DataResponseDTO {

	@SearchParameter(name = "client.fullName")
	private String clientName;

	private Long clientId;

	@SearchParameter(name = "product", provider = EnumValueProvider.class)
	private Product product;

	@SearchParameter(name = "status", provider = EnumValueProvider.class)
	private SaleStatus status;

	private Long sum;

	public SaleDTO(Sale sale) {
		this.id = sale.getId().toString();
		this.clientName = sale.getClient() == null ? null : sale.getClient().getFullName();
		this.product = sale.getProduct();
		this.status = sale.getStatus();
		this.sum = sale.getSum();
	}

}
