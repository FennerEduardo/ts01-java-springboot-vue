package com.example.transactionalsystemjavavue.application.sagas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot Saga Orchestrator.
 * Manages the lifecycle of the ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot saga through state transitions.
 * Each handler is transactional and idempotent.
 */
@Service
public class ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaOrchestrator {
    private static final Logger log = LoggerFactory.getLogger(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaOrchestrator.class);
    private final ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstanceRepository sagaRepository;
    private final ApplicationEventPublisher commandBus;

    public ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaOrchestrator(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstanceRepository sagaRepository, ApplicationEventPublisher commandBus) {
        this.sagaRepository = sagaRepository;
        this.commandBus = commandBus;
    }

    @Transactional
    public void handle(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootInitiatedEvent event) {
        log.info("Saga initiated: correlationId={}", event.correlationId());
        var metadata = event.metadata() != null ? event.metadata().toString() : "{}";
        var saga = new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance(event.correlationId(), event.procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId(), metadata);
        sagaRepository.save(saga);

        // Commands are dispatched in-process; bridge them to your broker (e.g. via the outbox) as needed.
        commandBus.publishEvent(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.AuthorizeProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(event.procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId(), event.metadata()));
    }

    @Transactional
    public void handle(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootAuthorizedEvent event) {
        log.info("Saga authorized: correlationId={}", event.correlationId());
        var saga = sagaRepository.findById(event.correlationId())
                .orElseThrow(() -> new IllegalArgumentException("Saga not found: " + event.correlationId()));

        saga.transitionTo(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState.AUTHORIZED);
        saga.transitionTo(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState.COMPLETING);
        sagaRepository.save(saga);

        commandBus.publishEvent(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.CompleteProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(saga.getProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId()));
    }

    @Transactional
    public void handle(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCompletedEvent event) {
        log.info("Saga completed: correlationId={}", event.correlationId());
        var saga = sagaRepository.findById(event.correlationId())
                .orElseThrow(() -> new IllegalArgumentException("Saga not found: " + event.correlationId()));

        saga.transitionTo(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState.COMPLETED);
        sagaRepository.save(saga);
    }

    @Transactional
    public void handle(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootFailedEvent event) {
        log.info("Saga failed: correlationId={}, reason={}", event.correlationId(), event.reason());
        var saga = sagaRepository.findById(event.correlationId())
                .orElseThrow(() -> new IllegalArgumentException("Saga not found: " + event.correlationId()));

        saga.transitionTo(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState.COMPENSATING);
        saga.setFailureReason(event.reason());

        commandBus.publishEvent(new ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaContract.CompensateProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(saga.getProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId(), event.reason()));

        saga.transitionTo(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState.FAILED);
        sagaRepository.save(saga);
    }
}
