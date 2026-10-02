package com.example.transactionalsystemjavavue.infrastructure.outbox;

public interface IMessageBrokerPublisher {
    void publish(String eventType, String payload) throws Exception;
}
