<template>
  <div>
    <div class="board-actions" v-if="!isLoading"> 
      <router-link
        :to="{ name: 'Board', params: { id: $route.params.boardID } }">Back to Board</router-link>
    </div>

    <h1>{{ card.title }}</h1>
    <p>{{ card.description }}</p>
    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <comments-list :comments="card.comments"  v-else />
  </div>
</template>

<script>
import boardService from '@/services/BoardService'
import CommentsList from '@/components/CommentsList'

export default {
  name: "card-detail",
  components: {
    CommentsList
  },
  data() {
    return {
      card : {
        title: '',
        description: '',
        status: '',
        comments: []
      },
      isLoading: true
    };
  },
  created() {
    const boardID = this.$route.params.boardID;
    const cardID = this.$route.params.cardID;

    boardService.getCard( boardID, cardID).then( responseCard => {
      this.card = responseCard;
      this.isLoading = false;
    }); 
  }
};
</script>
