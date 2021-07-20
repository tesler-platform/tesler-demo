package io.demo.response.client.data;

import io.demo.model.Contact;
import io.demo.response.client.dto.ContactDTO;
import io.tesler.core.service.ResponseService;

public interface ClientContactService extends ResponseService<ContactDTO, Contact> {
}
