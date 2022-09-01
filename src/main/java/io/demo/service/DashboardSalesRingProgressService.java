package io.demo.service;

import com.google.common.collect.ImmutableList;
import io.demo.dto.DashboardSalesRingProgressDTO;
import io.demo.entity.Sale;
import io.demo.entity.enums.SaleStatus;
import io.demo.repository.SaleRepository;
import io.tesler.api.data.ResultPage;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.model.core.entity.BaseEntity;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DashboardSalesRingProgressService extends
		VersionAwareResponseService<DashboardSalesRingProgressDTO, BaseEntity> {

	private final SaleRepository saleRepository;

	public DashboardSalesRingProgressService(SaleRepository saleRepository) {
		super(DashboardSalesRingProgressDTO.class, BaseEntity.class, null, DashboardSalesRingProgressMeta.class);
		this.saleRepository = saleRepository;
	}

	@Override
	public long count(BusinessComponent bc) {
		return 1L;
	}

	@Override
	public ResultPage<DashboardSalesRingProgressDTO> getList(BusinessComponent bc) {
		return ResultPage.of(ImmutableList.of(createSalesRingProgressDTO()), 1);
	}

	private DashboardSalesRingProgressDTO createSalesRingProgressDTO() {
		DashboardSalesRingProgressDTO dto = new DashboardSalesRingProgressDTO();
		List<Sale> sales = saleRepository.findAll();
		long allSalesSum = sales.stream().map(Sale::getSum).filter(Objects::nonNull).mapToLong(Long::longValue)
				.sum();
		long closedSalesSum = sales.stream()
				.filter(sale -> sale.getStatus() != null && sale.getStatus().equals(SaleStatus.Closed)).map(Sale::getSum)
				.filter(Objects::nonNull)
				.mapToLong(Long::longValue).sum();
		double percent;
		if (allSalesSum == 0) {
			percent = 0;
		} else {
			percent = (double) closedSalesSum / (double) allSalesSum;
		}
		dto.setSalesPercent(String.valueOf(percent));
		dto.setSalesSum("$" + closedSalesSum);
		dto.setSalesDescription("From $" + allSalesSum + " KPI sales");
		return dto;
	}

	@Override
	protected CreateResult<DashboardSalesRingProgressDTO> doCreateEntity(BaseEntity entity, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ActionResultDTO<DashboardSalesRingProgressDTO> doUpdateEntity(BaseEntity entity,
			DashboardSalesRingProgressDTO data,
			BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

}



