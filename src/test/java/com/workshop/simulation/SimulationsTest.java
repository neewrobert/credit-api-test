package com.workshop.simulation;

import com.workshop.BaseApiConfiguration;
import com.workshop.models.Simulation;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;

class SimulationsTest extends BaseApiConfiguration {

    @Test
    void shouldRetrieveAllSimulations() {
        when()
                .get("/simulations/")
                .then().statusCode(HttpStatus.SC_OK)
                .body("[0].id", notNullValue())
                .body("[0].name", is("Tom"))
                .body("[0].cpf", is("66414919004"))
                .body("[0].email", is("tom@gmail.com"))
                .body("[0].amount", is(new BigDecimal("11000.00")))
                .body("[0].installments", is(3))
                .body("[0].insurance", is(true));
    }

    @Test
    void shouldCreateNewSimulation() {
        var simulation = new Simulation(
                "Elias",
                "66414919004",
                "elias@gmail.com",
                new BigDecimal("11000.00"),
                5,
                true);

        given()
                .body(simulation)
                .contentType(ContentType.JSON)
        .when()
                .post("/simulations/")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", containsString(simulation.cpf()));
    }

    @Test
    void shouldDeleteExistingSimulation() {

        given()
                .pathParam("cpf", "66414919004")
                .when()
                .delete("/simulations/{cpf}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void shouldUpdateExistingSimulation() {
        var existingCpf = "66414919004";
        var simulation = new Simulation(
                "Elias Nogueira",
                "66414919004",
                "elias@gmail.com",
                new BigDecimal("2000"),
                5,
                true);

        var updated = given().pathParam("cpf", existingCpf)
                .body(simulation)
                .contentType(ContentType.JSON)
                .when()
                .put("/simulations/{cpf}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Simulation.class);

        assertThat(updated).isEqualTo(simulation);

    }
}
