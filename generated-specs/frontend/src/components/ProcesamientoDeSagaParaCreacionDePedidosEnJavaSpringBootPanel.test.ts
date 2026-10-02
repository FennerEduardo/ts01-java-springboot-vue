import { beforeEach, describe, expect, it, vi } from 'vitest';
import { flushPromises, mount } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel from './ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel.vue';
import { setProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient } from '../stores/procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot.store';
import type { ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient } from '../api/procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot-client';

describe('ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel', () => {
  beforeEach(() => setActivePinia(createPinia()));

  it('executes a command and lists the resulting event', async () => {
    const execute = vi.fn().mockResolvedValue({ type: 'Orderoutboxevents', aggregateId: 'agg-1', version: 1 });
    setProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient({ execute } as unknown as ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootClient);
    const wrapper = mount(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel);

    await wrapper.get('[data-test="aggregate-id"]').setValue('agg-1');
    await wrapper.get('[data-test="create_order"]').trigger('click');
    await flushPromises();

    expect(wrapper.text()).toContain('Orderoutboxevents v1');
    expect(execute).toHaveBeenCalledWith('agg-1', 'create_order', undefined, expect.objectContaining({ idempotencyKey: expect.any(String) }));
  });

  it('disables commands until an aggregate id is entered', () => {
    const wrapper = mount(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel);
    expect(wrapper.get('[data-test="create_order"]').attributes('disabled')).toBeDefined();
  });
});
