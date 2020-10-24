<template>
  <div class="state-selector">
      <label for="state">Select State:</label>
      <select id="state" v-model="state" @change="saveSelectedState">
          <option v-for="s in states" :key="s.code" :value="s.code">{{ s.name }}</option>
      </select>
  
  </div>
</template>

<script>
import statesService from '@/services/StatesService'

export default {
    name: 'state-selector',
    data() {
        return {
            state: '',
            states: []
        }
    },
    methods: {
        getStates() {
            statesService.getStates()
                .then( response => {
                    this.states = response.data;
                })
                .catch( err => console.error(err));
        },
        saveSelectedState() {
            this.$store.commit("SET_SELECTED_STATE", this.state);
        }
    },
    created() {
        this.getStates();
    }
}
</script>

<style>

</style>