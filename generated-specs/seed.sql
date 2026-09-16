-- Generated Seed SQL Fixtures for PostgreSQL / MySQL
-- Feature: Procesamiento de Saga para Creación de Pedidos en Java Spring Boot

INSERT INTO procesamiento_de_saga_para_creaci_n_de_pedidos_en_java_spring_boot (id, id, pago, comando, 00, idempotencykey, outbox)
VALUES (
  'f47ac10b-58cc-4372-a567-0e02b2c3d479', 'test', 'test', 'test', 'test', 'test', 'test'
) ON CONFLICT (id) DO NOTHING;
