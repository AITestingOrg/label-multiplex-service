package org.aist.aide.labelmultiplexer.domain.services;

import org.aist.aide.labelmultiplexer.domain.exceptions.NotFoundException;
import org.aist.aide.labelmultiplexer.domain.models.Label;
import org.aist.aide.labelmultiplexer.service.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class LabelService<T extends Label> {

    private Repo<T, Long> repo;

    public LabelService(@Autowired Repo<T, Long> repo) {
        this.repo = repo;
    }

    public List<T> getAll() {
        var list = new ArrayList<T>();
        repo.findAll().forEach(t -> list.add(t));
        return list;
    }

    public T get(long id) throws NotFoundException {
        return getObjWithCheck(id);
    }

    public void save(T obj) {
        repo.save(obj);
    }

    public void update(T obj) throws NotFoundException {
        doActionWithCheck(obj, t -> repo.save(t), obj.getId());
    }

    public void delete(long id) throws NotFoundException {
        doActionWithCheck(t -> repo.delete(t), id);
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
        var optional = repo.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException(String.format("Could not find anything with id %s", id));
    }
}
