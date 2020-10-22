<template>
  <div class="review-list">
      <review-display 
        v-for="review in filteredReviews" 
        v-bind:key="review.name" 
        v-bind:review="review" />
  </div>
</template>

<script>
import ReviewDisplay from "./ReviewDisplay";
export default {
    name: "review-list",
    components: {
        ReviewDisplay
    },
    computed: {
        filteredReviews() {
            const filter = this.$store.state.filter;
            const reviews = this.$store.state.reviews;
            return reviews.filter( review => {

                return filter === 0 ? true : filter === review.rating;
            });
        }
    }
}
</script>

<style>

</style>