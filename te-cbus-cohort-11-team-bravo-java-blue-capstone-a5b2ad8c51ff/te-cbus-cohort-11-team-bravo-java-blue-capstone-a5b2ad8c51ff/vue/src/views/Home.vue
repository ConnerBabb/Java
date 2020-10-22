<template>
  <div class="home">
    <h1>TECH ELEVATOR DIGITAL ASSISTANT</h1>
      <chat-box></chat-box>
  </div>
</template>

<script>
import ChatBox from '../components/ChatBox.vue'

export default {
  name: "home",
  components: {
    ChatBox
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap');
h1 {
 text-align: center;
 font-family: 'Share Tech Mono', monospace;
 
}
img {
  width: 250px;
}
</style>