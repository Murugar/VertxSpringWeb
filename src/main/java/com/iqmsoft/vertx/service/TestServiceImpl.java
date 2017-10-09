package com.iqmsoft.vertx.service;

import org.springframework.stereotype.Service;

import com.iqmsoft.vertx.domain.Test;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TestServiceImpl implements TestService {

    private final ConcurrentMap<String, Test> datastore;

    public TestServiceImpl() {
        this.datastore = new ConcurrentHashMap<>();
    }

    @Override
    public void saveFoo(Test foo) {
        datastore.put(foo.getId(), foo);
    }

    @Override
    public Collection<Test> findAll() {
        return datastore.values();
    }

}
