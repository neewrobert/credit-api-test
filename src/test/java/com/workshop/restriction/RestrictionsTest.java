package com.workshop.restriction;

import com.eliasnogueira.credit.model.MessageV1;
import com.workshop.api.service.RestrictionApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RestrictionsTest {

    private final RestrictionApiService restrictionService = new RestrictionApiService();

    @Test
    void shouldQueryCpfWithoutRestrictions() {

        //old code
        //leave it here for example

//        given()
//                .spec(cpfPathParameter("12345678900"))
//        .when()
//                .get("/restrictions/{cpf}")
//        .then()
//                .statusCode(HttpStatus.SC_NOT_FOUND);

        boolean isDeleted = restrictionService.queryCpf("12345678900");
        assertThat(isDeleted).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "97093236014", "60094146012", "84809766080", "62648716050", "26276298085",
            "01317496094", "55856777050", "55856777050", "24094592008", "58063164083"})
    void shouldReturnRestriction(String cpfWithRestriction) {
        //old code
        //leave it here for example
//        given()
//                .spec(cpfPathParameter(cpfWithRestriction))
//                .when()
//                .get("/restrictions/{cpf}")
//                .then()
//                .statusCode(HttpStatus.SC_OK);

        MessageV1 message = restrictionService.queryCpfWithRestriction("60094146012");
        assertThat(message.getMessage()).contains("60094146012");
    }
}
