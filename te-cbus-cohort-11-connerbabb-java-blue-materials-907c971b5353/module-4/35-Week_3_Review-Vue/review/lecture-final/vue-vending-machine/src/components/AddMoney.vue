<template>
  <div class="addMoney">
      <form v-on:submit.prevent="addToBalance">
          <label for="amount">Amount:</label>
          <input type="number" id="amount" v-model.number="amount" />
          <input type="submit" value="Add Money" />
      </form>
      <div class="currentBalance">Current Balance: ${{ currentBalance }}</div>
  </div>
</template>

<script>
export default {
    name: 'add-money',
    data() {
        return {
            amount: 0
        }
    },
    computed: {
        currentBalance() {
            return this.$store.state.balance.toFixed(2);
        }
    },
    methods: {
        addToBalance() {
            this.$store.commit("ADD_TO_BALANCE", this.amount);
        }
     }

}
</script>

<style>

</style>