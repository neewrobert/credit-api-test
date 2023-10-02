package com.workshop;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public abstract class BaseApiConfiguration {

    @BeforeAll
    static void mainConfiguration() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
        RestAssured.basePath = "/api/v1";

        RestAssured.config = RestAssuredConfig
                .newConfig().
                jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
