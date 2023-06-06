package io.demo.dto;

import io.demo.entity.Contact;
import io.tesler.api.data.dto.DataResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDTO extends DataResponseDTO {

	private String fullName;

	private String phoneNumber;

	private String email;

	public ContactDTO(Contact contact) {
		this.id = contact.getId().toString();
		this.fullName = contact.getFullName();
		this.phoneNumber = contact.getPhoneNumber();
		this.email = contact.getEmail();
	}

}
