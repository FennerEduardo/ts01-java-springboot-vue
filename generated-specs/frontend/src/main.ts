import { createApp } from 'vue';
import { createPinia } from 'pinia';
import ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel from './components/ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel.vue';

createApp(ProcesamientoDeSagaParaCreacionDePedidosEnJavaSpringBootPanel).use(createPinia()).mount('#app');
