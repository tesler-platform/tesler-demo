package io.demo.controller;

import io.demo.service.ClientContactService;
import io.demo.service.ClientReadResponseService;
import io.demo.service.ClientWriteResponseService;
import io.demo.service.MeetingReadResponseService;
import io.demo.service.MeetingWriteResponseService;
import io.demo.service.ClientPickListPopupService;
import io.demo.service.ContactPickListPopupService;
import io.demo.service.ResponsiblePickListPopupService;
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

	client(ClientReadResponseService.class),
		contact(client, ClientContactService.class),
	clientEdit(ClientWriteResponseService.class),
		contactEdit(clientEdit, ClientContactService.class),
	meeting(MeetingReadResponseService.class),
	meetingEdit(MeetingWriteResponseService.class),
		responsiblePickListPopup(meetingEdit, ResponsiblePickListPopupService.class),
		clientPickListPopup(meetingEdit, ClientPickListPopupService.class),
		contactPickListPopup(meetingEdit, ContactPickListPopupService.class);
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
