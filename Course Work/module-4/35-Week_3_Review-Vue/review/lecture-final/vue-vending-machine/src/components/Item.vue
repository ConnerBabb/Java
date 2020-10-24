<template>
  <div class="item">
      <h3>{{ item.name }}</h3>
      <div class="price">${{ item.price }}</div>
      <div class="code" v-on:click="buyItem(item)">{{ item.key }}</div>
  </div>
</template>

<script>
export default {
    name: "item",
    props: [ 'item' ],
    methods: {
       buyItem(item) {
          this.$store.commit("ADD_ITEM_TO_CART", item);
       }
    }
}
</script>

<style>
div.item {
   border: 1px black solid;
   margin-bottom: 1vh;
   padding-bottom: 1vh;
   background-color:aliceblue;
}
div.item h3 {
   background-color: lightblue;
   text-align: center;
   padding: 0px;
   margin: 0px;
}
div.item div {
   margin-top: 1vh;
   text-align: center;
}
div.item div.code {
   background-color: lightgray;
   width: 30%;
   margin: 0 auto;
   margin-top: 1vh;
   line-height: 20px;
   border: 1px gray solid;
   cursor: pointer;
}
div.item div.code:hover {
   color: blue;
   font-weight: 800;
}

</style>