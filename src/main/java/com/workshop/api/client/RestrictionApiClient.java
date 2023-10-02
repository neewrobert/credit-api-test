package com.workshop.api.client;

import com.eliasnogueira.credit.api.RestrictionsApi;
import com.workshop.api.RestApiClientBuilder;
import io.restassured.response.Response;

import java.util.function.Function;

public class RestrictionApiClient {
    private final RestrictionsApi restrictionsApi = new RestApiClientBuilder().build(RestrictionsApi::restrictions);

    public Response queryCpf(String cpf) {
        return restrictionsApi.oneUsingGET().cpfPath(cpf).execute(Function.identity());
    }
}
