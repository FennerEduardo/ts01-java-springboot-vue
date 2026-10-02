package com.example.transactionalsystemjavavue.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAggregateTest {

    @Test
    void startsInTheInitialStateWithNoEvents() {
        var aggregate = new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAggregate("agg-1");
        assertEquals(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootState.PENDING, aggregate.getState());
        assertEquals(0, aggregate.getVersion());
        assertTrue(aggregate.getPendingEvents().isEmpty());
    }

    @Test
    void rejectsAnAggregateWithoutId() {
        assertThrows(DomainValidationException.class, () -> new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAggregate(""));
    }

    @Test
    void createOrderRecordsOrderoutboxeventsAndBumpsTheVersion() {
        var aggregate = new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAggregate("agg-1");
        var event = aggregate.createOrder(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand("agg-1"));
        assertEquals(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootEventType.Orderoutboxevents, event.type());
        assertEquals(1, event.version());
        assertEquals(1, aggregate.getVersion());
        assertEquals(1, aggregate.getPendingEvents().size());
    }

    @Test
    void createOrderRejectsACommandWithoutId() {
        var aggregate = new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAggregate("agg-1");
        assertThrows(DomainValidationException.class, () -> aggregate.createOrder(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand("")));
        assertTrue(aggregate.getPendingEvents().isEmpty());
    }
}
