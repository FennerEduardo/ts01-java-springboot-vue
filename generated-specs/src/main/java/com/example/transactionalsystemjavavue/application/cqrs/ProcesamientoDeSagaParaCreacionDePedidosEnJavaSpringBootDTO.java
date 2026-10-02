package com.example.transactionalsystemjavavue.application.cqrs;

import java.util.UUID;

/**
 * Data Transfer Object for ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot read model.
 */
public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootDTO(UUID procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootId, String status, java.util.Map<String, Object> data) {}
