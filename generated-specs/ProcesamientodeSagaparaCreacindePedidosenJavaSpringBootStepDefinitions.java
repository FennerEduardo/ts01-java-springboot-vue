// Cucumber-JVM Step Definition Generator for Spring Boot & GraphQL
package com.example.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import static org.assertj.core.api.Assertions.assertThat;

public class ProcesamientodeSagaparaCreacindePedidosenJavaSpringBootStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    
    // Scenario: Creación e Integración Exitosa de Pedido con Notificación en Vue.js
    
    @Given("que existe un cliente activo con ID \"cust-java-001\"")
    public void GivenqueexisteunclienteactivoconIDcustjava001() {
        throw new io.cucumber.java.PendingException();
    }

    @When("se envía el comando \"CreateOrderCommand\" con monto 250.00 \"USD\" e idempotencyKey \"IDEM-JAVA-9988\"")
    public void WhenseenvaelcomandoCreateOrderCommandconmonto25000USDeidempotencyKeyIDEMJAVA9988() {
        throw new io.cucumber.java.PendingException();
    }

    @Then("el Saga Orchestration Service de Spring Boot debe registrar la transacción")
    public void ThenelSagaOrchestrationServicedeSpringBootdeberegistrarlatransaccin() {
        throw new io.cucumber.java.PendingException();
    }
    
}
