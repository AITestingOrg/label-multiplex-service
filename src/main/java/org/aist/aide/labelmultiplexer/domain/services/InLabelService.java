package org.aist.aide.labelmultiplexer.domain.services;

import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.service.repositories.InLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InLabelService extends LabelService<InLabel> {
    public InLabelService(@Autowired InLabelRepository inLabelRepository) {
        super(inLabelRepository);
    }
}
