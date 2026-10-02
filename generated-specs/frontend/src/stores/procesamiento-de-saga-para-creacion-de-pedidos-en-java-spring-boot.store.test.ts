import { beforeEach, describe, expect, it, vi } from 'vitest';
import { createPinia, setActivePinia } from 'pinia';
import { setProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient, useProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootStore } from './procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot.store';
import type { ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient } from '../api/procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot-client';

describe('procesamientoDeSagaParaCreacionDePedidosEnJavaSpringBoot store', () => {
  beforeEach(() => setActivePinia(createPinia()));

  it('records the event returned by the backend', async () => {
    const result = { type: 'Orderoutboxevents', aggregateId: 'agg-1', version: 1 };
    setProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient({ execute: vi.fn().mockResolvedValue(result) } as unknown as ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient);
    const store = useProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootStore();

    await store.execute('agg-1', 'create_order');

    expect(store.events).toEqual([result]);
    expect(store.error).toBeNull();
  });

  it('keeps the error message when the command fails', async () => {
    setProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient({ execute: vi.fn().mockRejectedValue(new Error('Command id is required')) } as unknown as ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient);
    const store = useProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootStore();

    await store.execute('agg-1', 'create_order');

    expect(store.error).toBe('Command id is required');
  });
});
