package io.demo.controller;

import io.demo.service.ClientContactService;
import io.demo.service.ClientReadResponseService;
import io.demo.service.ClientWriteResponseService;
import io.tesler.core.crudma.bc.BcIdentifier;
import io.tesler.core.crudma.bc.EnumBcIdentifier;
import io.tesler.core.crudma.bc.impl.AbstractEnumBcSupplier;
import io.tesler.core.crudma.bc.impl.BcDescription;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum TeslerRestController implements EnumBcIdentifier {

	// @formatter:off
	client(ClientReadResponseService.class),
	clientEdit(ClientWriteResponseService.class),
		contact(client, ClientContactService.class),
		contactEdit(clientEdit, ClientContactService.class)
	;

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
