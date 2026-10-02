package com.example.transactionalsystemjavavue.application.sagas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstanceRepository extends JpaRepository<ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance, UUID> {

    List<ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance> findByCurrentState(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance.SagaState state);

    @Query("SELECT s FROM ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance s WHERE s.currentState IN ('COMPENSATING', 'STARTED') AND s.retryCount < :maxRetries")
    List<ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootSagaInstance> findRetryableSagas(int maxRetries);
}
