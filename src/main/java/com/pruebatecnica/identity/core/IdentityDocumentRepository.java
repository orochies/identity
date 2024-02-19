package com.pruebatecnica.identity.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentRepository extends MongoRepository<IdentityDocument, String> {

}
