package com.example.transactionalsystemjavavue.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderSagaAggregateTest {

    @Test
    void startsInTheInitialStateWithNoEvents() {
        var aggregate = new OrderSagaAggregate("agg-1");
        assertEquals(OrderSagaState.PENDING, aggregate.getState());
        assertEquals(0, aggregate.getVersion());
        assertTrue(aggregate.getPendingEvents().isEmpty());
    }

    @Test
    void rejectsAnAggregateWithoutId() {
        assertThrows(DomainValidationException.class, () -> new OrderSagaAggregate(""));
    }

    @Test
    void createOrderRecordsOrderoutboxeventsAndBumpsTheVersion() {
        var aggregate = new OrderSagaAggregate("agg-1");
        var event = aggregate.createOrder(new OrderSagaCommand("agg-1"));
        assertEquals(OrderSagaEventType.Orderoutboxevents, event.type());
        assertEquals(1, event.version());
        assertEquals(1, aggregate.getVersion());
        assertEquals(1, aggregate.getPendingEvents().size());
    }

    @Test
    void createOrderRejectsACommandWithoutId() {
        var aggregate = new OrderSagaAggregate("agg-1");
        assertThrows(DomainValidationException.class, () -> aggregate.createOrder(new OrderSagaCommand("")));
        assertTrue(aggregate.getPendingEvents().isEmpty());
    }
}
