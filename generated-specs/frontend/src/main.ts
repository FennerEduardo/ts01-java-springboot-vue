import { createApp, h } from 'vue';
import { createPinia } from 'pinia';
import OrderSagaPanel from './components/OrderSagaPanel.vue';

const App = { render: () => [h(OrderSagaPanel)] };

createApp(App).use(createPinia()).mount('#app');
