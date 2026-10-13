import { defineStore } from 'pinia';
import { ref } from 'vue';
import { CommandName, CommandResult, createOrderSagaClient, OrderSagaClient } from '../api/order-saga-client';

let client: OrderSagaClient = createOrderSagaClient({ baseUrl: import.meta.env.VITE_API_URL ?? '' });

/** Test/composition hook: swap the API client (e.g. a fake in unit tests). */
export function setOrderSagaClient(next: OrderSagaClient): void {
  client = next;
}

export const useOrderSagaStore = defineStore('orderSaga', () => {
  const events = ref<CommandResult[]>([]);
  const loading = ref(false);
  const error = ref<string | null>(null);

  async function execute(id: string, command: CommandName, payload?: Record<string, unknown>): Promise<void> {
    loading.value = true;
    error.value = null;
    try {
      events.value.push(await client.execute(id, command, payload, { idempotencyKey: crypto.randomUUID() }));
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'Request failed';
    } finally {
      loading.value = false;
    }
  }

  return { events, loading, error, execute };
});
