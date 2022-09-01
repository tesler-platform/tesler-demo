package io.demo.service;

import io.demo.dto.DashboardSalesFunnelDTO;
import io.demo.repository.ClientRepository;
import io.demo.repository.MeetingRepository;
import io.demo.repository.SaleRepository;
import io.tesler.api.data.ResultPage;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.model.core.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardSalesFunnelService extends VersionAwareResponseService<DashboardSalesFunnelDTO, BaseEntity> {

	private final ClientRepository clientRepository;

	private final MeetingRepository meetingRepository;

	private final SaleRepository saleRepository;

	final String clients_key = "All active Clients";

	final String activities_key = "Preparatory Activities";

	final String meetings_key = "Number of Meetings";

	final String sales_key = "Number of Sales";

	final String clients_color = "#779FE9";

	final String activities_color = "#8FAFE9";

	final String meetings_color = "#5F90EA";

	final String sales_color = "#4D83E7";

	public DashboardSalesFunnelService(ClientRepository clientRepository, MeetingRepository meetingRepository,
			SaleRepository saleRepository) {
		super(DashboardSalesFunnelDTO.class, BaseEntity.class, null, DashboardSalesFunnelMeta.class);
		this.clientRepository = clientRepository;
		this.meetingRepository = meetingRepository;
		this.saleRepository = saleRepository;
	}

	@Override
	public long count(BusinessComponent bc) {
		return 1L;
	}

	@Override
	public ResultPage<DashboardSalesFunnelDTO> getList(BusinessComponent bc) {
		List<DashboardSalesFunnelDTO> salesFunnelDTOS = createSalesFunnelDTOS();
		return ResultPage.of(salesFunnelDTOS, salesFunnelDTOS.size());
	}

	private List<DashboardSalesFunnelDTO> createSalesFunnelDTOS() {
		List<DashboardSalesFunnelDTO> salesFunnelDTOS = new ArrayList<>();
		long activitiesAmount = clientRepository.count() + meetingRepository.count();
		salesFunnelDTOS.add(new DashboardSalesFunnelDTO(clients_key, clientRepository.count(), clients_color));
		salesFunnelDTOS.add(new DashboardSalesFunnelDTO(activities_key, activitiesAmount, activities_color));
		salesFunnelDTOS.add(new DashboardSalesFunnelDTO(meetings_key, meetingRepository.count(), meetings_color));
		salesFunnelDTOS.add(new DashboardSalesFunnelDTO(sales_key, saleRepository.count(), sales_color));
		return salesFunnelDTOS;
	}

	@Override
	protected CreateResult<DashboardSalesFunnelDTO> doCreateEntity(BaseEntity entity, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ActionResultDTO<DashboardSalesFunnelDTO> doUpdateEntity(BaseEntity entity, DashboardSalesFunnelDTO data,
			BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

}



