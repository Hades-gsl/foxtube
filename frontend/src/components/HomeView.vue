<script setup>

import AppBar from "@/components/AppBar.vue";
import VideoGrid from "@/components/VideoGrid.vue";
import Banner from "@/components/Banner.vue";
import {reactive, ref} from "vue";
import {getHttp} from "@/scripts/http.js";
import {store} from "@/scripts/store.js";

let video_count = ref(0)

getHttp().get('/videos/count').then(response => {
  video_count.value = response.data.count
}).catch(error => {
  console.error(error)
})

function get_videos(page) {
  let res = null
  getHttp().get('/videos', {
    params: {
      offset: (page - 1) * store.videos_per_count
    }
  }).then(response => {
    res = response
  }).catch(error => {
    console.log(error)
  })

  return res.data
}

let videos = reactive(get_videos(1))

</script>


<template>
  <v-layout class="rounded rounded-md">
    <AppBar/>

    <v-main class="" style="min-height: 300px;">
      <Banner/>

      <video-grid :videos="videos" :video_count="video_count" :get_videos="get_videos"/>
    </v-main>
  </v-layout>
</template>

<style scoped>

</style>