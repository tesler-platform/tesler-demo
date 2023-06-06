package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.dto.SaleDTO;
import io.demo.entity.Sale;
import io.demo.repository.SaleRepository;
import io.tesler.core.crudma.bc.BusinessComponent;
import io.tesler.core.crudma.impl.VersionAwareResponseService;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.ActionResultDTO;
import io.tesler.core.dto.rowmeta.CreateResult;
import io.tesler.core.dto.rowmeta.PostAction;
import io.tesler.core.service.action.ActionScope;
import io.tesler.core.service.action.Actions;
import org.springframework.stereotype.Service;

@Service
public class SaleReadService extends VersionAwareResponseService<SaleDTO, Sale> {

	private final SaleRepository saleRepository;

	public SaleReadService(SaleRepository saleRepository) {
		super(SaleDTO.class, Sale.class, null, SaleMeta.class);
		this.saleRepository = saleRepository;
	}

	@Override
	protected CreateResult<SaleDTO> doCreateEntity(Sale entity, BusinessComponent bc) {
		saleRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						"/screen/sale/view/saleedit/" + TeslerRestController.saleEdit + "/" + entity.getId()
				));
	}

	@Override
	protected ActionResultDTO<SaleDTO> doUpdateEntity(Sale entity, SaleDTO data, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Actions<SaleDTO> getActions() {
		return Actions.<SaleDTO>builder()
				.create().text("Add").add()
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.scope(ActionScope.RECORD)
				.invoker((bc, data) -> new ActionResultDTO<SaleDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/sale/view/saleedit/" + TeslerRestController.saleEdit + "/" + bc.getId()
						)))
				.add()
				.build();
	}

	@Override
	public boolean isDeferredCreationSupported(BusinessComponent bc) {
		return false;
	}

}
