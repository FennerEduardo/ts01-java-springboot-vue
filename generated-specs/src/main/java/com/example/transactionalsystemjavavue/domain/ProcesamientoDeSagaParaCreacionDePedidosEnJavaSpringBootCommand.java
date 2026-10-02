package com.example.transactionalsystemjavavue.domain;

import java.util.Map;

public record ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(String id, Map<String, Object> payload) {
    public ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootCommand(String id) {
        this(id, Map.of());
    }
}
