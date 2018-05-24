package org.aist.aide.labelmultiplexer.service.repositories;

import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InLabelRepository extends CrudRepository<InLabel, Long> {
    Optional<InLabel> findByName(String name);
}
