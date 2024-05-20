<script setup>

import AppBar from "@/components/AppBar.vue";
import VideoGrid from "@/components/VideoGrid.vue";
import Banner from "@/components/Banner.vue";

const videos = [
  {id: 1, title: '视频1', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者1'},
  {id: 2, title: '视频2', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者2'},
  {id: 3, title: '视频3', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者3'},
  {id: 4, title: '视频4', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者4'},
  {id: 5, title: '视频5', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者5'},
  {id: 6, title: '视频6', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者6'},
  {id: 7, title: '视频7', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者7'},
]

</script>


<template>
  <v-layout class="rounded rounded-md">
    <AppBar/>

    <v-main class="" style="min-height: 300px;">
      <Banner/>

      <video-grid :videos="videos"/>
    </v-main>
  </v-layout>
</template>

<style scoped>

</style>