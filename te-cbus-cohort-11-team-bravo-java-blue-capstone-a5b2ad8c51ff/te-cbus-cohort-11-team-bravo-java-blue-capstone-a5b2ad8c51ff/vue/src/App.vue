<template>
  <div id="app" >
    <div id="nav">
    <a href="/">End Chat Session</a>
    </div>
    <router-view />
    <div>
      <img src="https://static1.squarespace.com/static/55ef2da9e4b03f6e1ef0cd28/t/5dceda76b702913cebc1d05e/1596634240606/?format=1500w" alt="TECHELEVATOR">
    </div>
  </div>
</template>


<style>
body{
  background-color: lightcyan;
}
.app {
  display: flex;
}
img{
  position: fixed;
  bottom: 10px;
  right: 10px;

}
</style>