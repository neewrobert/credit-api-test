package com.workshop.api.service;

import com.eliasnogueira.credit.model.MessageV1;
import com.workshop.api.client.RestrictionApiClient;
import org.apache.http.HttpStatus;

public class RestrictionApiService {
    private RestrictionApiClient restrictionsApiClient = new RestrictionApiClient();

    /**
     * Query CPF without a restriction
     */
    public boolean queryCpf(String cpf) {
        restrictionsApiClient.queryCpf(cpf).then().statusCode(HttpStatus.SC_NOT_FOUND);
        return true;
    }

    public MessageV1 queryCpfWithRestriction(String cpf) {
        return restrictionsApiClient.queryCpf(cpf).then().
                statusCode(HttpStatus.SC_OK).extract().as(MessageV1.class);
    }
}
