package com.workshop.restriction;

import com.workshop.BaseApiConfiguration;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.workshop.specs.SharedRequestSpecs.cpfPathParameter;
import static io.restassured.RestAssured.given;

class RestrictionsTest extends BaseApiConfiguration {

    @Test
    void shouldQueryCpfWithoutRestrictions() {

        given()
                .spec(cpfPathParameter("12345678900"))
        .when()
                .get("/restrictions/{cpf}")
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "97093236014", "60094146012", "84809766080", "62648716050", "26276298085",
            "01317496094", "55856777050", "55856777050", "24094592008", "58063164083"})
    void shouldReturnRestriction(String cpfWithRestriction) {

        given()
                .spec(cpfPathParameter(cpfWithRestriction))
        .when()
                .get("/restrictions/{cpf}")
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
