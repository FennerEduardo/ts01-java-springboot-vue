# language: es
Característica: Procesamiento de Saga para Creación de Pedidos en Java Spring Boot

  Escenario: Creación e Integración Exitosa de Pedido con Notificación en Vue.js
    Dado que existe un cliente activo con ID "cust-java-001"
    Y la pasarela de pago "STRIPE" está disponible
    Cuando se envía el comando "CreateOrderCommand" con monto 250.00 "USD" e idempotencyKey "IDEM-JAVA-9988"
    Entonces el Saga Orchestration Service de Spring Boot debe registrar la transacción
    Y se debe insertar un registro en la tabla Outbox "order_outbox_events"
    Y la store de Pinia en Vue.js debe actualizar el estado del pedido a "PAID"
    Y se debe emitir un hash SHA-256 de auditoría en la respuesta
