package com.iqmsoft.vertx.web.controller.v1;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqmsoft.vertx.domain.Test;
import com.iqmsoft.vertx.service.TestService;
import com.iqmsoft.vertx.web.controller.v1.dto.TestDTO;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TestController {

    @Autowired
    private TestService fooService;

    public void create(RoutingContext routingContext) {
        TestDTO fooDTO = Optional.ofNullable(routingContext.getBodyAsJson())
                .map(bodyAsJson -> bodyAsJson.mapTo(TestDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("Empty body provided"));

        fooDTO.setId(UUID.randomUUID().toString());

        Test foo = mapToDomain(fooDTO);
        fooService.saveFoo(foo);

        routingContext.response()
                .setStatusCode(HttpResponseStatus.CREATED.code())
                .end(Json.encode(fooDTO));
    }

    public void findAll(RoutingContext routingContext) {
        Collection<Test> allFoos = fooService.findAll();
        Collection<TestDTO> allFooDTOs = allFoos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        routingContext.response()
                .end(Json.encode(allFooDTOs));
    }

    private Test mapToDomain(TestDTO fooDTO) {
        return new Test(
                fooDTO.getId(),
                fooDTO.getBar());
    }

    private TestDTO mapToDTO(Test foo) {
        return new TestDTO(
                foo.getId(),
                foo.getBar());
    }

}
