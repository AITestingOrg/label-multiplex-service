package org.aist.aide.labelmultiplexer.domain.services;

import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.service.repositories.InLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InLabelService extends CrudLabelService<InLabel> {
    protected InLabelRepository labelRepository;

    public InLabelService(@Autowired InLabelRepository inLabelRepository) {
        super(inLabelRepository);
        labelRepository = inLabelRepository;
    }

    public InLabel getByName(String name) throws NotFoundException {
        var optional = labelRepository.findByName(name);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException(String.format("Could not find a label by name %s", name));
    }
}
