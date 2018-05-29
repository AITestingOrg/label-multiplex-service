package org.aist.aide.labelmultiplexer.service.repositories;

import java.util.Optional;

import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InLabelRepository extends CrudRepository<InLabel, Long> {
    Optional<InLabel> findByName(String name);
}
