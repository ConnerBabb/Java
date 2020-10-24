<template>
  <div>
    <div class="favorite-all">
        <label for="favoriteAll">Change All</label>
        <input type="checkbox" id="favoriteAll" v-model="favoriteAllValue" v-on:change="switchFavoriteOnAll" />
    </div>
    <table>
        <tr>
            <th>Title</th>
            <th>Reviewer</th>
            <th>Review</th>
            <th>Rating</th>
            <th>Favorited</th>
        </tr>
        <review-table-row v-for="nextReview in filteredReviews" v-bind:key="nextReview.title" v-bind:review="nextReview" />
        <tr v-show="filteredReviews.length == 0">
            <td colspan="5">There are no reviews</td>
        </tr>
    </table>
  </div>
</template>

<script>
import ReviewTableRow from './ReviewTableRow'

export default {
    name: "review-table",
    components: {
        ReviewTableRow
    },
    data() {
        return {
            favoriteAllValue: false
        }
    },
    computed: {
        filteredReviews() {
            const filter = this.$store.state.filter;
            const reviews = this.$store.state.reviews;
            return reviews.filter( review => {

                return filter === 0 ? true : filter === review.rating;
            });
        }
    },
    methods: {
        switchFavoriteOnAll() {
            this.$store.commit("CHANGE_ALL_FAVORITES", this.favoriteAllValue);
        }
    }
}
</script>

<style style="scoped">
th,
td {
 text-align: left;
}
td {
 padding-right: 10px;
 vertical-align: top;
}
tr:nth-child(even) {
 background-color: rgb(238, 238, 238);
}
.favorite-all {
    display: flex;
    justify-content: flex-end;
}
div.favorite-all > input {
    margin-left: 10px;
}
</style>