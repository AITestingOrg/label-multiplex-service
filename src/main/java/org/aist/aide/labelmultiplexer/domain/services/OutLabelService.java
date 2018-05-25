package org.aist.aide.labelmultiplexer.domain.services;

import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.service.repositories.OutLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutLabelService extends CrudLabelService<OutLabel> {
    public OutLabelService(@Autowired OutLabelRepository outLabelRepository) {
        super(outLabelRepository);
    }
}
