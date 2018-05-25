package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.domain.services.OutLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/out-label")
public class OutLabelController extends LabelController<OutLabel> {
    public OutLabelController(@Autowired OutLabelService outLabelService) {
        super(outLabelService);
    }
}
