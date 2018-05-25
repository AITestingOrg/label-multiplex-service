package org.aist.aide.labelmultiplexer.service.repositories;

import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutLabelRepository extends CrudRepository<OutLabel, Long> {
}
