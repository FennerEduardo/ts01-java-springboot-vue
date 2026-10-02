package com.example.transactionalsystemjavavue.application.sagas;

import java.util.UUID;

/**
 * Saga Contract for ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot domain process.
 * Contains all events and commands involved in the saga orchestration.
 */
public class ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract {

    // === Events ===
    public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootInitiatedEvent(UUID correlationId, UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, java.util.Map<String, Object> metadata) {}
    public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAuthorizedEvent(UUID correlationId) {}
    public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCompletedEvent(UUID correlationId) {}
    public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootFailedEvent(UUID correlationId, String reason) {}

    // === Commands ===
    public record AuthorizeProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, java.util.Map<String, Object> metadata) {}
    public record CompleteProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId) {}
    public record CompensateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, String reason) {}
}
