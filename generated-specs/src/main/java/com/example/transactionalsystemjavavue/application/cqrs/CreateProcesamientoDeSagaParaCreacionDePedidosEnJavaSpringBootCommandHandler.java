package com.example.transactionalsystemjavavue.application.cqrs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

/**
 * Command Handler for CreateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot.
 * Follows CQRS: writes to the write model and publishes domain events.
 */
@Service
public class CreateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommandHandler {
    private final ApplicationEventPublisher eventPublisher;

    public CreateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommandHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public UUID handle(CreateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand command) {
        UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId = UUID.randomUUID();

        // 1. Save to Write Model (Domain DB)
        // repository.save(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot(procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, command.tenantId(), command.payload()));

        // 2. Publish Domain Event
        eventPublisher.publishEvent(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCreatedEvent(procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, command.tenantId()));
        return procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId;
    }
}
