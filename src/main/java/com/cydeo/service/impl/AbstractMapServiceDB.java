package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapServiceDB<ID,T> {

    Map<ID,T> map = new HashMap<>();

    void save(ID id, T object) { // create

        map.put(id,object);

    }


    List<T> readAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    void deleteById(ID id){

        map.remove(id);

    }

    void update(ID id, T object){
        map.put(id,object);
    }




}
