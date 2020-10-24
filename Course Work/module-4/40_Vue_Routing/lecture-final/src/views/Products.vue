<template>
  <div class="products">
      <h1>Products</h1>
      <product-list />
  </div>
</template>

<script>
import ProductList from '@/components/ProductsList';

export default {
    name: 'products',
    components: {
        ProductList
    }    
}
</script>

<style>

</style>