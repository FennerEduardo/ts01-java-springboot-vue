import { beforeEach, describe, expect, it, vi } from 'vitest';
import { createPinia, setActivePinia } from 'pinia';
import { setOrderSagaClient, useOrderSagaStore } from './order-saga.store';
import type { OrderSagaClient } from '../api/order-saga-client';

describe('orderSaga store', () => {
  beforeEach(() => setActivePinia(createPinia()));

  it('records the event returned by the backend', async () => {
    const result = { type: 'Orderoutboxevents', aggregateId: 'agg-1', version: 1 };
    setOrderSagaClient({ execute: vi.fn().mockResolvedValue(result) } as unknown as OrderSagaClient);
    const store = useOrderSagaStore();

    await store.execute('agg-1', 'create_order');

    expect(store.events).toEqual([result]);
    expect(store.error).toBeNull();
  });

  it('keeps the error message when the command fails', async () => {
    setOrderSagaClient({ execute: vi.fn().mockRejectedValue(new Error('Command id is required')) } as unknown as OrderSagaClient);
    const store = useOrderSagaStore();

    await store.execute('agg-1', 'create_order');

    expect(store.error).toBe('Command id is required');
  });
});
