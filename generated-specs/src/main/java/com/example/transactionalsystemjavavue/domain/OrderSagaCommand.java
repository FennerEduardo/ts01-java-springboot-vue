package com.example.transactionalsystemjavavue.domain;

import java.util.Map;

public record OrderSagaCommand(String id, Map<String, Object> payload) {
    public OrderSagaCommand(String id) {
        this(id, Map.of());
    }
}
