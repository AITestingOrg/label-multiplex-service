package org.aist.aide.labelmultiplexer.service.controllers;

import java.util.List;
import javax.validation.ValidationException;

import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.domain.services.OutLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/out-label")
public class OutLabelController {
    private OutLabelService crudLabelService;

    public OutLabelController(@Autowired OutLabelService outLabelService) {
        this.crudLabelService = outLabelService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<OutLabel>> getAll() {
        return new ResponseEntity<>(crudLabelService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<OutLabel> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(crudLabelService.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody OutLabel label) {
        try {
            crudLabelService.save(label);
            return new ResponseEntity<>(label.getId(), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody OutLabel label) {
        try {
            crudLabelService.update(label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        try {
            crudLabelService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
