package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.domain.services.LabelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/out-label")
public class OutLabelController extends LabelController<OutLabel> {
    public OutLabelController(LabelService<OutLabel> labelService) {
        super(labelService);
    }
}
