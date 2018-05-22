package org.aist.aide.labelmultiplexer.service.controllers;

import org.aist.aide.labelmultiplexer.domain.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.Label;
import org.aist.aide.labelmultiplexer.domain.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

public abstract class LabelController<T extends Label> {
    private LabelService<T> labelService;

    public LabelController(@Autowired LabelService<T> labelService) {
        this.labelService = labelService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(labelService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<T> get(@PathVariable long id) {
        try {
            return new ResponseEntity(labelService.get(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody T label) {
        try {
            labelService.save(label);
            return new ResponseEntity<>(label.getId(), HttpStatus.OK);
        } catch(ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody T label) {
        try {
            labelService.update(label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        try {
            labelService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
