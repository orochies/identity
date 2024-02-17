package com.pruebatecnica.identity.core;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentityDocumentRepository extends MongoRepository<IdentityDocument, Integer> {

}
