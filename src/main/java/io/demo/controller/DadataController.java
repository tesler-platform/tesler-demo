package io.demo.controller;

import io.demo.dto.request.ProposalsRequestDto;
import io.demo.dto.response.ProposalsResponseDto;
import io.demo.service.DadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class DadataController {

	private final DadataService dadataService;

	@PostMapping
	ProposalsResponseDto getCompanyProposals(@RequestBody ProposalsRequestDto rqDto) {
		return dadataService.getProposals(rqDto);
	}


}
