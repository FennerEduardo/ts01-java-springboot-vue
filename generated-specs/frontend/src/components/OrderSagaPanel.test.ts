import { beforeEach, describe, expect, it, vi } from 'vitest';
import { flushPromises, mount } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import OrderSagaPanel from './OrderSagaPanel.vue';
import { setOrderSagaClient } from '../stores/order-saga.store';
import type { OrderSagaClient } from '../api/order-saga-client';

describe('OrderSagaPanel', () => {
  beforeEach(() => setActivePinia(createPinia()));

  it('executes a command and lists the resulting event', async () => {
    const execute = vi.fn().mockResolvedValue({ type: 'Orderoutboxevents', aggregateId: 'agg-1', version: 1 });
    setOrderSagaClient({ execute } as unknown as OrderSagaClient);
    const wrapper = mount(OrderSagaPanel);

    await wrapper.get('[data-test="aggregate-id"]').setValue('agg-1');
    await wrapper.get('[data-test="create_order"]').trigger('click');
    await flushPromises();

    expect(wrapper.text()).toContain('Orderoutboxevents v1');
    expect(execute).toHaveBeenCalledWith('agg-1', 'create_order', undefined, expect.objectContaining({ idempotencyKey: expect.any(String) }));
  });

  it('disables commands until an aggregate id is entered', () => {
    const wrapper = mount(OrderSagaPanel);
    expect(wrapper.get('[data-test="create_order"]').attributes('disabled')).toBeDefined();
  });
});
