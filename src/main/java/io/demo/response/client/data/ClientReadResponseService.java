package io.demo.response.client.data;

import io.demo.model.Client;
import io.demo.response.client.dto.ClientAbstractDTO;
import io.demo.response.client.dto.ClientReadDTO;
import io.tesler.core.service.ResponseService;

public interface ClientReadResponseService extends ResponseService<ClientReadDTO, Client> {
}
