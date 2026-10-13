package com.example.transactionalsystemjavavue.api;

import com.example.transactionalsystemjavavue.auth.JwtTenantFilter;
import com.example.transactionalsystemjavavue.domain.DomainValidationException;
import com.example.transactionalsystemjavavue.runtime.CommandService;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** POST /api/v1/order-saga/{id}/{command} (create_order). */
// ghk:implements OrderSaga
@RestController
@RequestMapping("/api/v1/order-saga")
public class OrderSagaController {
    private static final Map<String, HttpStatus> STATUS = Map.of("created", HttpStatus.ACCEPTED, "replayed", HttpStatus.OK, "in-progress", HttpStatus.CONFLICT);
    private final CommandService commands;

    public OrderSagaController(CommandService commands) {
        this.commands = commands;
    }

    @PostMapping("/{id}/{command}")
    public ResponseEntity<CommandService.Result> handle(
        @PathVariable("id") String id,
        @PathVariable("command") String command,
        @RequestBody(required = false) Map<String, Object> payload,
        @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
        @RequestHeader(value = "traceparent", required = false) String traceparent,
        @RequestAttribute(JwtTenantFilter.TENANT_ATTRIBUTE) String tenantId
    ) throws SQLException {
        CommandService.Result result = commands.handle(new CommandService.Request(tenantId, id, command, payload == null ? Map.of() : payload, idempotencyKey, traceparent, "OrderSaga"));
        return ResponseEntity.status(STATUS.getOrDefault(result.status(), HttpStatus.OK)).body(result);
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<Map<String, String>> invalid(DomainValidationException e) {
        return ResponseEntity.unprocessableEntity().body(Map.of("detail", String.valueOf(e.getMessage())));
    }
}
