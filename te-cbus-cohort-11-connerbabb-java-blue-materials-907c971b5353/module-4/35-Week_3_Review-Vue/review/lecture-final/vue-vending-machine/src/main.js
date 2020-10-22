import Vue from 'vue'
import VendingMachine from './views/VendingMachine'
import store from './store'

Vue.config.productionTip = false

new Vue({
  store,
  render: h => h(VendingMachine)
}).$mount('#app')
