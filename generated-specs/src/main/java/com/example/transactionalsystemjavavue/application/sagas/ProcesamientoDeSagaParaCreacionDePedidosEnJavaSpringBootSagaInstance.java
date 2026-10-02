package com.example.transactionalsystemjavavue.application.sagas;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Persistent Saga state entity for ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot orchestration.
 * Tracks the current step and allows recovery after failures.
 */
@Entity
@Table(name = "saga_instances", indexes = {
    @Index(name = "idx_saga_state", columnList = "currentState"),
    @Index(name = "idx_saga_type", columnList = "sagaType")
})
public class ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance {
    @Id
    private UUID correlationId;

    @Column(nullable = false)
    private String sagaType = "ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SagaState currentState;

    private UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    private String failureReason;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private int retryCount = 0;

    public enum SagaState { STARTED, AUTHORIZED, COMPLETING, COMPLETED, COMPENSATING, COMPENSATED, FAILED }

    public ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance() {}

    public ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance(UUID correlationId, UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, String metadata) {
        this.correlationId = correlationId;
        this.procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId = procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId;
        this.metadata = metadata;
        this.currentState = SagaState.STARTED;
    }

    public UUID getCorrelationId() { return correlationId; }
    public String getSagaType() { return sagaType; }
    public SagaState getCurrentState() { return currentState; }
    public void transitionTo(SagaState state) {
        this.currentState = state;
        this.updatedAt = Instant.now();
    }
    public UUID getProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId() { return procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId; }
    public String getMetadata() { return metadata; }
    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String reason) { this.failureReason = reason; }
    public int getRetryCount() { return retryCount; }
    public void incrementRetry() { this.retryCount++; this.updatedAt = Instant.now(); }
}
