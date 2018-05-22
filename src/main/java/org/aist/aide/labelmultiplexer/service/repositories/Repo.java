package org.aist.aide.labelmultiplexer.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repo<T, K> extends MongoRepository<T, K> {
}
