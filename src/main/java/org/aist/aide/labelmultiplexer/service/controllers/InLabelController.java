package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.domain.services.InLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InLabelController {
    protected InLabelService crudLabelService;

    public InLabelController(@Autowired InLabelService inLabelService) {
        crudLabelService = inLabelService;
    }

    @RequestMapping("in-label/name/{name}")
    public ResponseEntity<InLabel> getByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(crudLabelService.getByName(name), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/in-label")
    public ResponseEntity<List<InLabel>> getAll() {
        return new ResponseEntity<>(crudLabelService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("in-label/{id}")
    public ResponseEntity<InLabel> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(crudLabelService.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/in-label", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody InLabel label) {
        try {
            crudLabelService.save(label);
            return new ResponseEntity<>(label.getId(), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/in-label", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody InLabel label) {
        try {
            crudLabelService.update(label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/in-label/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        try {
            crudLabelService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
