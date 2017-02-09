package com.example.service;

import java.util.List;

/**
 * Created by EgorPC on 07.02.2017.
 */
public interface GenericService <T, ID> {

    T add(T obj);

    T edit(T obj);

    void delete(T obj);

    void deleteAll();

    T get(ID id);

    List<T> getAll();

}
