package com.workshop.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SharedRequestSpecs {
    public static RequestSpecification cpfPathParameter(String cpf) {
        return new RequestSpecBuilder().addPathParams("cpf", cpf).build();
    }
}
