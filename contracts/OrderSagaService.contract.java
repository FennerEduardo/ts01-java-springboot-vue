package com.transactional.system.domain.saga;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface OrderSagaServiceContract {

    record CreateOrderCommand(
        UUID orderId,
        UUID customerId,
        String sourceSystem,
        BigDecimal totalAmount,
        String currency,
        String idempotencyKey
    ) {}

    record OrderCreatedEvent(
        UUID eventId,
        UUID orderId,
        UUID customerId,
        BigDecimal amount,
        Instant occurredAt,
        String correlationId
    ) {}

    record PaymentAuthorizedEvent(
        UUID eventId,
        UUID orderId,
        UUID paymentId,
        String gatewayTransactionId,
        String status
    ) {}

    record ProcessSagaResult(
        UUID sagaId,
        String sagaStatus,
        String currentStep,
        boolean isCompleted
    ) {}

    ProcessSagaResult executeOrderSaga(CreateOrderCommand command);
}
