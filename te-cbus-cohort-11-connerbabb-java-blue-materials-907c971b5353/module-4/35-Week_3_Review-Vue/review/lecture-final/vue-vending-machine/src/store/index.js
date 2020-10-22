import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    items: [],
    cart: [],
    balance: 0
  },
  mutations: {
    ADD_ALL_ITEMS(state, items) {
      state.items = items;
    },
    ADD_ITEM_TO_CART(state, item) {
      state.cart.push(item);
      state.balance -= item.price;
    },
    ADD_TO_BALANCE(state, amount) {
      state.balance += amount;
    }
  },
  actions: {
    getProducts( { commit } ) {
      fetch("inventory.json")
        .then( response => {
          return response.json();
        })
        .then( items => {
          commit("ADD_ALL_ITEMS", items);
        })
        .catch ( err => console.error(err) );
    }
  },
  modules: {
  }
})
