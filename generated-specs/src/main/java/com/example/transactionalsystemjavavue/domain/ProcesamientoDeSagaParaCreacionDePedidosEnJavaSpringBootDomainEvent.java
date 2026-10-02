package com.example.transactionalsystemjavavue.domain;

import java.time.Instant;
import java.util.Map;

public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootDomainEvent(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootEventType type, String aggregateId, long version, Instant occurredOn, Map<String, Object> payload) {
}
