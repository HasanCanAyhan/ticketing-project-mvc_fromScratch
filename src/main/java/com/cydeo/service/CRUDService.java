package com.cydeo.service;

import java.util.List;

public interface CRUDService<ID,T> { // create -> here : save , read , update, delete

    void save(ID id , T object);

    List<T> readAll();

    T findById(ID id);

    void deleteById(ID id);

    void update(ID id, T object);




}
