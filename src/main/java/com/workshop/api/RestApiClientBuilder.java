package com.workshop.api;

import io.restassured.builder.RequestSpecBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

public class RestApiClientBuilder {
    public <T> T build(Function<Supplier<RequestSpecBuilder>, T> clientCreator) {
        Supplier<RequestSpecBuilder> requestSpecBuilderSupplier = () -> new RequestSpecBuilder()
                .addRequestSpecification(
                        new RequestSpecBuilder()
                                .setBaseUri("http://localhost")
                                .setPort(8088)
                                .build());

        return clientCreator.apply(requestSpecBuilderSupplier);
    }
}
