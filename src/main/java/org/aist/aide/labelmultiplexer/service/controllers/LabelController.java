package org.aist.aide.labelmultiplexer.service.controllers;

import java.util.List;
import javax.validation.ValidationException;

import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.Label;
import org.aist.aide.labelmultiplexer.domain.services.CrudLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class LabelController<T extends Label> {
    protected CrudLabelService<T> crudLabelService;

    protected LabelController(@Autowired CrudLabelService<T> crudLabelService) {
        this.crudLabelService = crudLabelService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(crudLabelService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<T> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(crudLabelService.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody T label) {
        try {
            crudLabelService.save(label);
            return new ResponseEntity<>(label.getId(), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody T label) {
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
