<template>
  <div class="cart">
      <h2>Shopping Cart</h2>
      <div>Total: ${{ cartTotal }}</div>
      <div class="cart-items">
          <div class="cartItem" v-for="item in cartItems" v-bind:key="item.id">
              <h3>{{ item.name}}</h3>
              <div>{{ item.price }}</div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name: 'shopping-cart',
    computed: {
        cartItems() {
            return this.$store.state.cart;
        },
        cartTotal() {
            const itemsInCart = this.$store.state.cart;
            return itemsInCart.reduce( (total, item) => {
                return total += parseFloat(item.price);
            }, 0).toFixed(2);
        }
    }
}
</script>

<style>
div.cart-items {
   display: flex;
   justify-content: flex-start;
}
div.cartItem {
   border: 1px black solid;
   margin: 1vw;
   padding: 1vw;
   text-align: center;
}
div.cartItem h3
{
   margin: 0;
   padding: 0;
}

</style>