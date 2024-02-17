package com.pruebatecnica.identity.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IdentityDocumentService {

	public IdentityDocumentDto create(IdentityDocumentDto dto);

	public Page<IdentityDocumentDto> read(Pageable pageable);

	public IdentityDocumentDto update(IdentityDocumentDto dto);

	public int delete(int id);

}
