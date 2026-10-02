<script setup lang="ts">
import { ref } from 'vue';
import { COMMANDS } from '../api/procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot-client';
import { useProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootStore } from '../stores/procesamiento-de-saga-para-creacion-de-pedidos-en-java-spring-boot.store';

const store = useProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootStore();
const aggregateId = ref('');
</script>

<template>
  <section aria-label="Procesamiento de Saga para Creación de Pedidos en Java Spring Boot">
    <h1>Procesamiento de Saga para Creación de Pedidos en Java Spring Boot</h1>
    <label>
      Aggregate id
      <input v-model="aggregateId" data-test="aggregate-id" />
    </label>
    <button
      v-for="command in COMMANDS"
      :key="command"
      :data-test="command"
      :disabled="!aggregateId || store.loading"
      @click="store.execute(aggregateId, command)"
    >
      {{ command }}
    </button>
    <p v-if="store.error" role="alert">{{ store.error }}</p>
    <ul aria-label="events">
      <li v-for="e in store.events" :key="e.aggregateId + '-' + e.version">{{ e.type }} v{{ e.version }}</li>
    </ul>
  </section>
</template>
