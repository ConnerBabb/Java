<template>
  <div class="park-list">
      <h1>{{ totalParks}} Parks Found in {{ state }}</h1>
      <div v-if="isLoading">Loading Parks...</div>
      <div v-else>
        <park-list-item v-for="park in parks" :key="park.id" :park="park" />

      </div>
  </div>
</template>

<script>
import parkService from '@/services/ParkService'
import ParkListItem from '@/components/ParkListItem'

export default {
    name: 'park-list',
    props: ['state'],
    components: {
        ParkListItem
    },
    data() {
        return {
            parks: [],
            isLoading: true,
            totalParks: 0
        }
    },
    methods: {
        getParksForState(state) {
            if (state) {
                parkService.getParks(state)
                    .then( response => {
                        this.totalParks = response.data.total;
                        this.parks = response.data.data;
                        console.log(this.parks)
                        this.isLoading = false;
                    })
                    .catch( err => console.error(err) );
            }
        }
    },
    created() {
        this.getParksForState(this.state);
    },
    watch: {
        state: function() {
            this.isLoading = true;
            this.getParksForState(this.state);
        }
    }
}
</script>

<style>

</style>