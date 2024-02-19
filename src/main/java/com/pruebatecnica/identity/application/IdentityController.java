package com.pruebatecnica.identity.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.identity.config.AppConfig;
import com.pruebatecnica.identity.core.IdentityDocumentDto;
import com.pruebatecnica.identity.core.IdentityDocumentService;

@RestController
@RequestMapping(AppConfig.API_PATH + "/identities")
public class IdentityController {

	@Autowired
	private IdentityDocumentService service;

	public IdentityController(IdentityDocumentService service) {
		this.service = service;
	}

	@GetMapping()
	public Page<IdentityDocumentDto> findAll(Pageable pageable) {

		return this.service.read(pageable);
	}

	@PostMapping()
	public IdentityDocumentDto create(@RequestBody(required = true) IdentityDocumentDto dto) {

		return this.service.create(dto);
	}

	@PutMapping()
	public IdentityDocumentDto update(@RequestBody(required = true) IdentityDocumentDto dto) {

		return this.service.update(dto);
	}

	@DeleteMapping()
	public String delete(@RequestParam(name = "id", required = true) String id) {

		return this.service.delete(id);
	}

}
