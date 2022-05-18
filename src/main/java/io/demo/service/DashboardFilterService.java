

package io.demo.service;

import io.demo.dto.DashboardFilterDTO;
import io.demo.dto.DashboardFilterDTO_;
import io.demo.entity.DashboardFilter;
import io.demo.entity.DashboardFilter_;
import io.demo.entity.enums.FieldOfActivity;
import io.demo.repository.DashboardFilterRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.multivalue.MultivalueField;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.service.action.Actions;
import io.tesler.core.util.session.SessionService;
import io.tesler.model.core.entity.User;
import io.tesler.model.core.entity.User_;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class
DashboardFilterService extends VersionAwareResponseService<DashboardFilterDTO, User> {

	private final SessionService sessionService;

	private final DashboardFilterRepository dashboardFilterRepository;

	public DashboardFilterService(SessionService sessionService,
			DashboardFilterRepository dashboardFilterRepository) {
		super(DashboardFilterDTO.class, User.class, null, DashboardFilterMeta.class);
		this.sessionService = sessionService;
		this.dashboardFilterRepository = dashboardFilterRepository;
	}

	@Override
	public long count(BusinessComponent bc) {
		return 1L;
	}

	@Override
	protected Specification<User> getSpecification(BusinessComponent bc) {
		sessionService.getSessionUser().getId();
		Specification<User> filterSpecification = (root, cq, cb) -> cb.equal(
				root.get(User_.id),
				sessionService.getSessionUser().getId()
		);
		return super.getSpecification(bc).and(filterSpecification);

	}

	@Override
	protected CreateResult<DashboardFilterDTO> doCreateEntity(User entity, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ActionResultDTO<DashboardFilterDTO> doUpdateEntity(User entity, DashboardFilterDTO data,
			BusinessComponent bc) {
		DashboardFilter filter = dashboardFilterRepository.findOne((root, cq, cb) -> cb.equal(
				root.get(DashboardFilter_.userId),
				sessionService.getSessionUser().getId()
		)).orElse(null);
		if (filter == null) {
			filter = new DashboardFilter();
			filter.setUserId(sessionService.getSessionUser().getId());
			dashboardFilterRepository.save(filter);
		}
		if (data.isFieldChanged(DashboardFilterDTO_.fieldOfActivity)) {
			filter.setFieldOfActivities(
					data.getFieldOfActivity().getValues()
							.stream()
							.map(v -> FieldOfActivity.getByValue(v.getValue()))
							.collect(Collectors.toSet()));
		}
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	protected DashboardFilterDTO entityToDto(BusinessComponent bc, User entity) {
		DashboardFilterDTO dto = super.entityToDto(bc, entity);
		DashboardFilter dashboardFilter = dashboardFilterRepository.findOne(
				(root, cq, cb) -> cb.equal(root.get(DashboardFilter_.userId), sessionService.getSessionUser().getId())
		).orElse(null);
		if (dashboardFilter != null) {
			dto.setFieldOfActivity(dashboardFilter.getFieldOfActivities()
					.stream()
					.collect(MultivalueField.toMultivalueField(
							Enum::name,
							FieldOfActivity::getValue
					)));
		}
		return dto;
	}

	@Override
	public Actions<DashboardFilterDTO> getActions() {

		return Actions.<DashboardFilterDTO>builder()
				.action("filter", "Apply Filters")
				.invoker((bc, dto) -> new ActionResultDTO<>())
				.available(bc -> true).withAutoSaveBefore()
				.add()
				.build();
	}

}


