package io.demo.controller;

import io.demo.service.data.ClientContactService;
import io.demo.service.data.ClientReadResponseService;
import io.demo.service.data.ClientWriteResponseService;
import io.tesler.core.crudma.bc.BcIdentifier;
import io.tesler.core.crudma.bc.EnumBcIdentifier;
import io.tesler.core.crudma.bc.impl.AbstractEnumBcSupplier;
import io.tesler.core.crudma.bc.impl.BcDescription;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum ServiceAssociation implements EnumBcIdentifier {

	// @formatter:off
	client(ClientReadResponseService.class),
	clientEdit(ClientWriteResponseService.class),
		contact(client, ClientContactService.class),
		contactEdit(clientEdit, ClientContactService.class)
	;

	// @formatter:on

	public static final EnumBcIdentifier.Holder<ServiceAssociation> Holder = new Holder<>(ServiceAssociation.class);

	private final BcDescription bcDescription;

	ServiceAssociation(String parentName, Class<?> serviceClass, boolean refresh) {
		this.bcDescription = buildDescription(parentName, serviceClass, refresh);
	}

	ServiceAssociation(String parentName, Class<?> serviceClass) {
		this(parentName, serviceClass, false);
	}

	ServiceAssociation(BcIdentifier parent, Class<?> serviceClass, boolean refresh) {
		this(parent == null ? null : parent.getName(), serviceClass, refresh);
	}

	ServiceAssociation(BcIdentifier parent, Class<?> serviceClass) {
		this(parent, serviceClass, false);
	}

	ServiceAssociation(Class<?> serviceClass, boolean refresh) {
		this((String) null, serviceClass, refresh);
	}

	ServiceAssociation(Class<?> serviceClass) {
		this((String) null, serviceClass, false);
	}

	@Component
	public static class BcSupplier extends AbstractEnumBcSupplier<ServiceAssociation> {

		public BcSupplier() {
			super(ServiceAssociation.Holder);
		}

	}

}
