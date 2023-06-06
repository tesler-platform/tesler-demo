package io.demo.service;

import io.demo.controller.TeslerRestController;
import io.demo.dto.SaleDTO;
import io.demo.dto.SaleDTO_;
import io.demo.entity.Sale;
import io.demo.repository.ClientRepository;
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
public class SaleWriteService extends VersionAwareResponseService<SaleDTO, Sale> {

	private final SaleRepository saleRepository;

	private final ClientRepository clientRepository;

	public SaleWriteService(SaleRepository SaleRepository, ClientRepository clientRepository) {
		super(SaleDTO.class, Sale.class, null, SaleWriteMeta.class);
		this.saleRepository = SaleRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	protected CreateResult<SaleDTO> doCreateEntity(Sale entity, BusinessComponent bc) {
		saleRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/sale/view/salelist/"
						+ TeslerRestController.saleEdit
						+ "/" + entity.getId()
		));
	}

	@Override
	protected ActionResultDTO<SaleDTO> doUpdateEntity(Sale entity, SaleDTO data, BusinessComponent bc) {
		if (data.isFieldChanged(SaleDTO_.clientId)) {
			if (data.getClientId() != null) {
				entity.setClient(clientRepository.getById(data.getClientId()));
			} else {
				entity.setClient(null);
			}
		}
		if (data.isFieldChanged(SaleDTO_.product)) {
			entity.setProduct(data.getProduct());
		}
		if (data.isFieldChanged(SaleDTO_.status)) {
			entity.setStatus(data.getStatus());
		}
		if (data.isFieldChanged(SaleDTO_.sum)) {
			entity.setSum(data.getSum());
		}
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public Actions<SaleDTO> getActions() {
		return Actions.<SaleDTO>builder()
				.newAction()
				.scope(ActionScope.RECORD)
				.withAutoSaveBefore()
				.action("saveAndContinue", "Save")
				.invoker((bc, dto) -> new ActionResultDTO<SaleDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/sale/view/salelist/" + TeslerRestController.sale + "/" + bc.getId()
						)))
				.add()
				.newAction()
				.action("cancel", "Cancel")
				.scope(ActionScope.BC)
				.withoutAutoSaveBefore()
				.invoker((bc, dto) -> new ActionResultDTO<SaleDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/sale/"
						)))
				.add()
				.build();
	}

}
