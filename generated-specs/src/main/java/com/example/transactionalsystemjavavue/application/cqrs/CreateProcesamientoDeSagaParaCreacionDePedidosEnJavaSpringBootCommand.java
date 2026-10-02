package com.example.transactionalsystemjavavue.application.cqrs;

import java.util.UUID;

/**
 * CQRS Commands for ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot domain.
 * Commands are immutable records (Java 21) that represent intent.
 */
public record CreateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(UUID tenantId, java.util.Map<String, Object> payload) {}
