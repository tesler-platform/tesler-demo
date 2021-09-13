package io.demo.controller;

import io.demo.service.ClientContactService;
import io.demo.service.ClientReadResponseService;
import io.demo.service.ClientWriteResponseService;
import io.demo.service.Meeting.MeetingReadResponseService;
import io.demo.service.Meeting.MeetingWriteResponseService;
import io.demo.service.Meeting.PickListPopup.ClientPickListPopupService;
import io.demo.service.Meeting.PickListPopup.ContactPickListPopupService;
import io.demo.service.Meeting.PickListPopup.ResponsiblePickListPopupService;
import io.tesler.core.crudma.bc.BcIdentifier;
import io.tesler.core.crudma.bc.EnumBcIdentifier;
import io.tesler.core.crudma.bc.impl.AbstractEnumBcSupplier;
import io.tesler.core.crudma.bc.impl.BcDescription;
import lombok.Getter;
import org.springframework.stereotype.Component;

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
