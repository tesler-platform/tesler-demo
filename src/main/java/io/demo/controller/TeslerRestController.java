package io.demo.controller;

import io.demo.service.DashboardClientActivitiesService;
import io.demo.service.ClientContactService;
import io.demo.service.ClientReadService;
import io.demo.service.ClientWriteService;
import io.demo.service.ContactPickListService;
import io.demo.service.DashboardFilterService;
import io.demo.service.MeetingReadService;
import io.demo.service.MeetingWriteService;
import io.demo.service.ClientPickListService;
import io.demo.service.ResponsiblePickListService;
import io.demo.service.DashboardService;
import io.demo.service.SaleReadService;
import io.demo.service.SaleWriteService;
import io.tesler.core.crudma.bc.BcIdentifier;
import io.tesler.core.crudma.bc.EnumBcIdentifier;
import io.tesler.core.crudma.bc.impl.AbstractEnumBcSupplier;
import io.tesler.core.crudma.bc.impl.BcDescription;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * This is actually an analog of a usual @RestController.
 * When you add enum, you just add rest endpoints, that tesler UI can call.
 * We could actually make a usual @RestController and make it Generic,
 * but current enum approach shows, that it is less error-prone in huge enterprise projects
 * (because single line in this enum creates >5 rest endpoints)
 */
@Getter
public enum TeslerRestController implements EnumBcIdentifier {

	// @formatter:on

	client(ClientReadService.class),
		contact(client, ClientContactService.class),
	clientEdit(ClientWriteService.class),
		contactEdit(clientEdit, ClientContactService.class),
		contactEditAssoc(clientEdit, ClientContactService.class),
	meeting(MeetingReadService.class),
	meetingEdit(MeetingWriteService.class),
		responsiblePickListPopup(meetingEdit, ResponsiblePickListService.class),
		clientPickListPopup(meetingEdit, ClientPickListService.class),
		contactPickListPopup(meetingEdit, ContactPickListService.class),
	sale(SaleReadService.class),
	saleEdit(SaleWriteService.class),
		clientSalePickListPopup(saleEdit, ClientPickListService.class),
	dashboardFilter(DashboardFilterService.class),
		dashboardClientActivities(dashboardFilter, DashboardClientActivitiesService.class),
	dashboardSalesFunnel(dashboardFilter, DashboardService.class),
	dashboardSalesRingProgress(dashboardFilter, DashboardService.class);

	// @formatter:on

	public static final EnumBcIdentifier.Holder<TeslerRestController> Holder = new Holder<>(TeslerRestController.class);

	private final BcDescription bcDescription;

	TeslerRestController(String parentName, Class<?> serviceClass, boolean refresh) {
		this.bcDescription = buildDescription(parentName, serviceClass, refresh);
	}

	TeslerRestController(String parentName, Class<?> serviceClass) {
		this(parentName, serviceClass, false);
	}

	TeslerRestController(BcIdentifier parent, Class<?> serviceClass, boolean refresh) {
		this(parent == null ? null : parent.getName(), serviceClass, refresh);
	}

	TeslerRestController(BcIdentifier parent, Class<?> serviceClass) {
		this(parent, serviceClass, false);
	}

	TeslerRestController(Class<?> serviceClass, boolean refresh) {
		this((String) null, serviceClass, refresh);
	}

	TeslerRestController(Class<?> serviceClass) {
		this((String) null, serviceClass, false);
	}

	@Component
	public static class BcSupplier extends AbstractEnumBcSupplier<TeslerRestController> {

		public BcSupplier() {
			super(TeslerRestController.Holder);
		}

	}

}
