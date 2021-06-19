package fr.iut.blankproject.service;

import java.util.List;

public interface CrudService<ID, T>  {
    List<T> findAll();

    T findById(ID id);

    T save(T toSave);

    void deleteById(ID id);

    T update(ID id, T toUpdate);
}
