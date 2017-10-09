package com.iqmsoft.vertx.service;

import java.util.Collection;

import com.iqmsoft.vertx.domain.Test;

public interface TestService {

    void saveFoo(Test foo);

    Collection<Test> findAll();

}
