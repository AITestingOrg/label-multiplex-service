package org.aist.aide.labelmultiplexer.service.repositories;


import org.springframework.data.repository.CrudRepository;

public interface Repo<T, K> extends CrudRepository<T, K> {
}
