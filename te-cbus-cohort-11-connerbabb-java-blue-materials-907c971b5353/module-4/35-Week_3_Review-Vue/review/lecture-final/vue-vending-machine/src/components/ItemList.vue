<template>
  <div class="item-list">
      <h2>{{ title }}</h2>
      <div class="image"><img v-bind:src="getImageUrl(title)" v-on:click="showItems = !showItems" /> </div>
      <div v-if="showItems">
        <item v-for="item in filteredItems" v-bind:key="item.key" v-bind:item="item" />
      </div>
  </div>
</template>

<script>
import Item from './Item'

export default {
    name: 'item-list',
    props: [ 'itemType', 'title'],
    components: {
        Item
    },
    data() {
        return {
            showItems: false
        }
    },
    computed: {
        filteredItems() {
            const items = this.$store.state.items;
            return items.filter( item => {
                return item.code === this.itemType;
            });
        }
    },
    methods: {
        getImageUrl(title) {
            return require('../assets/' + title + '.png');
        }
    }
}
</script>

<style>
div.item-list {
   background-color: lightskyblue;
}
div.item-list h2 {
   text-align: center;
   margin: 0;
   padding: 0;
   margin-bottom: 2vh;
 
}
div.image {
   text-align: center;
   margin-bottom: 2vh;
}
img {
   border-radius: 80px;
   width: 40%;
   border: 2px blue dotted;
}

</style>