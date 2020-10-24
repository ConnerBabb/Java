<template>
    <div id="app">
        <h1>Vue Vending Machine</h1>
        <div class="item-lists">
            <item-list itemType="A" v-bind:title="appName" />
            <item-list itemType="D" title="Desserts" />
            <item-list itemType="E" title="Entrees" />
            <item-list itemType="B" title="Beverages" />
        </div>
        <add-money />
        <shopping-cart />
    </div>
</template>

<script>
import ItemList from '@/components/ItemList'
import ShoppingCart from '@/components/ShoppingCart'
import AddMoney from '@/components/AddMoney'

export default {
    name: "vending-machine",
    components: {
        ItemList,
        ShoppingCart,
        AddMoney
    },
    data() {
        return {
            appName: 'Appetizers'
        }
    },
    created() {
        this.$store.dispatch("getProducts");
    }
}
</script>

<style>
div#app div.item-lists{
   display: flex;
   justify-content: space-between;
   flex-wrap: wrap;
}
div#app .item-list {
   border: 1px black solid;
   padding-left: 2vw;
   padding-right: 2vw;
   margin: 10px;
   min-width: 200px;
 
}

</style>