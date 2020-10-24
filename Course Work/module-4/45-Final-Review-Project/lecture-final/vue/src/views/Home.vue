<template>
  <div class="home">
    <h1>Home</h1>
    <state-selector />
    <park-list v-bind:state="selectedState" />
  </div>
</template>

<script>
import StateSelector from '@/components/StateSelector'
import ParkList from '@/components/ParkList'

export default {
  name: "home",
  components: {
    StateSelector,
    ParkList

  },
  computed: {
    selectedState() {
      return this.$store.state.selectedState;
    }
  }
};
</script>
