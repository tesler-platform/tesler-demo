

package io.demo.service;

import io.demo.dto.DashboardClientActivitiesDTO;
import io.demo.entity.Client;
import io.demo.entity.Client_;
import io.demo.entity.DashboardFilter;
import io.demo.entity.DashboardFilter_;
import io.demo.entity.enums.FieldOfActivity;
import io.demo.repository.DashboardFilterRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import java.util.Set;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class
DashboardClientActivitiesService extends VersionAwareResponseService<DashboardClientActivitiesDTO, Client> {

	private final DashboardFilterRepository dashboardFilterRepository;

	public DashboardClientActivitiesService(DashboardFilterRepository dashboardFilterRepository) {
		super(DashboardClientActivitiesDTO.class, Client.class, null, DashboardClientActivitiesMeta.class);
		this.dashboardFilterRepository = dashboardFilterRepository;
	}


	@Override
	protected Specification<Client> getSpecification(BusinessComponent bc) {
		return super.getSpecification(bc).and(getFilterSpecification(bc));
	}

	private Specification<Client> getFilterSpecification(BusinessComponent bc) {
		DashboardFilter dashboardFilter = dashboardFilterRepository.findOne(
				(root, cq, cb) -> cb.equal(root.get(
						DashboardFilter_.userId), bc.getParentIdAsLong())
		).orElse(null);
		if (dashboardFilter == null) {
			return (root, cq, cb) -> cb.and();
		}
		Set<FieldOfActivity> filteredActivities = dashboardFilter.getFieldOfActivities();
		if (filteredActivities.isEmpty()) {
			return (root, cq, cb) -> cb.and();
		}
		return (root, cq, cb) -> {
			Join<Client, FieldOfActivity> activitiesJoin = root.join(Client_.fieldOfActivities, JoinType.INNER);
			return activitiesJoin.in(filteredActivities);
		};
	}

	@Override
	protected CreateResult<DashboardClientActivitiesDTO> doCreateEntity(Client entity, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ActionResultDTO<DashboardClientActivitiesDTO> doUpdateEntity(Client entity, DashboardClientActivitiesDTO data,
			BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected DashboardClientActivitiesDTO entityToDto(BusinessComponent bc, Client entity) {
		DashboardClientActivitiesDTO dto = super.entityToDto(bc, entity);
		dto.setNumberOfOpenActivities((long) entity.getFieldOfActivities().size());
		return dto;
	}

}

