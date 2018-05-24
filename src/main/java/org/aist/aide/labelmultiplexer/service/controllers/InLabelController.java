package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.domain.services.InLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/in-label")
public class InLabelController extends LabelController<InLabel> {

    public InLabelController(@Autowired InLabelService inLabelService) {
        super(inLabelService);
    }
}
