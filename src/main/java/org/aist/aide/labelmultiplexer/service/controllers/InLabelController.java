package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.domain.services.InLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/in-label")
public class InLabelController extends LabelController<InLabel> {
    protected InLabelService crudLabelService;
    public InLabelController(@Autowired InLabelService inLabelService) {
        super(inLabelService);
        crudLabelService = inLabelService;
    }

    @RequestMapping("name/{name}")
    public ResponseEntity<InLabel> getByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(crudLabelService.getByName(name), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
