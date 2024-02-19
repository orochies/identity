package com.pruebatecnica.identity.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pruebatecnica.identity.core.IdentityDocument;
import com.pruebatecnica.identity.core.IdentityDocumentDto;
import com.pruebatecnica.identity.core.IdentityDocumentRepository;
import com.pruebatecnica.identity.core.IdentityDocumentService;

@Service
public class IdentityDocumentRegister implements IdentityDocumentService {

	private static final Logger log = LoggerFactory.getLogger(IdentityDocumentRegister.class);

	private IdentityDocumentRepository repo;

	public IdentityDocumentRegister(IdentityDocumentRepository repo) {
		this.repo = repo;
	}

	@Override
	public IdentityDocumentDto create(IdentityDocumentDto dto) {

		log.debug("--- Create new document {}", dto);

		IdentityDocument doc = dto.toDocument();

		doc = repo.save(doc);

		return new IdentityDocumentDto(doc);
	}

	@Override
	public Page<IdentityDocumentDto> read(Pageable pageable) {

		log.debug("--- Find all documents paginated");

		Page<IdentityDocument> pages = repo.findAll(pageable);

		List<IdentityDocumentDto> list = pages.stream().map(IdentityDocumentDto::new).toList();

		log.debug("--- cantidad documentos {} ", list.size());

		return new PageImpl<>(list, pageable, pages.getTotalElements());
	}

	@Override
	public IdentityDocumentDto update(IdentityDocumentDto dto) {

		log.debug("--- Update document with params {}", dto);

		IdentityDocument doc = dto.toDocument();

		doc = repo.save(doc);

		return new IdentityDocumentDto(doc);
	}

	@Override
	public String delete(String id) {

		log.debug("--- Delete document with id {}", id);

		repo.deleteById(id);

		return id;
	}

}
