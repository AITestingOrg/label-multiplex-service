package org.aist.aide.labelmultiplexer.domain.services;

import org.aist.aide.labelmultiplexer.domain.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class LabelService<T extends Label> {
    private CrudRepository<T, Long> labelRepository;

    public LabelService(CrudRepository<T, Long> labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<T> getAll() {
        var list = new ArrayList<T>();
        labelRepository.findAll().forEach(t -> list.add(t));
        return list;
    }

    public T get(long id) throws NotFoundException {
        return getObjWithCheck(id);
    }

    public void save(T obj) {
        labelRepository.save(obj);
    }

    public void update(T obj) throws NotFoundException {
        doActionWithCheck(obj, t -> labelRepository.save(t), obj.getId());
    }

    public void delete(long id) throws NotFoundException {
        doActionWithCheck(t -> labelRepository.delete(t), id);
    }

    private void doActionWithCheck(Consumer<T> func, long id) throws NotFoundException {
        var t = getObjWithCheck(id);
        func.accept(t);
    }

    private void doActionWithCheck(T obj, Consumer<T> func, long id) throws NotFoundException {
        getObjWithCheck(id);
        func.accept(obj);
    }

    private T getObjWithCheck(long id) throws NotFoundException {
        var optional = labelRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException(String.format("Could not find anything with id %s", id));
    }
}
