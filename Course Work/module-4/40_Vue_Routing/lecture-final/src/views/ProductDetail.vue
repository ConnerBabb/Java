<template>
    <div id="app" class="main">
        <h1>{{ product.name }}</h1>
        <p class="description">{{ product.description }}</p>

        <div class="well-display">
            <average-summary />
            <star-summary rating="1" />
            <star-summary rating="2" />
            <star-summary rating="3" />
            <star-summary rating="4" />
            <star-summary rating="5" />
        </div>
        <div class="actions">
            <router-link v-bind:to="{ name: 'products' }">Back to Products</router-link>
            &nbsp;|
            <router-link :to="{ name: 'add-review', params: { id: product.id} } ">
                Add Review
            </router-link>
        </div>
        <review-list />
    </div>
</template>

<script>
import AverageSummary from '@/components/AverageSummary';
import StarSummary from '@/components/StarSummary';
import AddReview from '@/components/AddReview.vue';
import ReviewList from '@/components/ReviewList.vue';

export default {
    name: 'product-detail',
    components: {
      AverageSummary,
      StarSummary,
      AddReview,
      ReviewList
    },

    computed: {
        product() {
            return this.$store.state.products.find(
                p => {
                    return p.id == this.$store.state.activeProduct;
                }
            )
        }
    },
    created() {
        const activeProductId = this.$route.params.id;
        this.$store.commit("SET_ACTIVE_PRODUCT", activeProductId);
    }
}
</script>

<style>

</style>