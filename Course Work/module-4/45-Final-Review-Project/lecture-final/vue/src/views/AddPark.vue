<template>
  <div>
      <p v-if="addStatus">{{ addStatus }}</p>
      <h2 ><a :href="park.url" v-html="park.name">{{ park.name}}</a></h2>
      <img :src="park.images[0].url" />
      <p>{{ park.description }}</p>
      <button v-on:click="addPark">Do you want to add this Park</button>
  </div>
</template>

<script>
import favoritesService from '@/services/FavoritesService'

export default {
    data() {
        return {
            park: {},
            addStatus: ''
        }
    },
    created() {
        const parkCode = this.$route.params.parkCode;
        const currentPark = this.$store.state.currentPark;
        console.log(currentPark)
        if (currentPark.parkCode == parkCode) {
            this.park = currentPark;
        }
    },
    methods: {
        addPark() {
            const favoritePark = {
                name: this.park.name,
                state: this.$store.state.selectedState,
                description: this.park.description ,
                url: this.park.url,
                imageUrl: this.park.images[0].url
            }
            favoritesService.addToFavorites(favoritePark)
                .then ( response => {
                    if (response.status == 201) {
                        this.addStatus = "Added Successfully!";
                    } else {
                        this.addStatus = "Did not add successfully";
                        console.log(response);
                    }
                })
                .catch( err => console.error(err) )
        }
    }
}
</script>

<style>

</style>