<template>
  <div class="parkListItem">
      <h2 ><a :href="park.url" v-html="park.name">{{ park.name}}</a></h2>
      <img :src="park.images[0].url" /><button v-on:click.prevent="addParkToFavorites">Add To Favorites</button>
      <p>{{ park.description }}</p>
  </div>
</template>

<script>
export default {
    name: 'park-list-item',
    props: ['park'],
    methods: {
        addParkToFavorites() {
            this.$store.commit("SET_CURRENT_PARK", this.park);
            this.$router.push({ name: 'addpark', params: { parkCode: this.park.parkCode }})
        }
    }
}
</script>

<style>
img {
    width: 400px;
}
</style>