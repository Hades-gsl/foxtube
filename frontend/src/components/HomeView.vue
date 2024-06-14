<script setup>

import AppBar from "@/components/AppBar.vue";
import VideoGrid from "@/components/VideoGrid.vue";
import Banner from "@/components/Banner.vue";
import {onMounted, ref} from "vue";
import {getHttp} from "@/scripts/http.js";
import {store} from "@/scripts/store.js";

async function get_video_count() {
  let res = await getHttp().get('/video/count')

  if (res.status === 200) {
    return res.data.count
  }

  return 0;
}

const video_count = ref(0)

async function get_videos(page) {
  let res = await getHttp().get('/video', {
    params: {
      offset: (page - 1) * store.videos_per_count
    }
  })

  if (res.status === 200) {
    return res.data
  }

  return [];
}

const videos = ref([])

onMounted(async () => {
  video_count.value = await get_video_count()
  videos.value = await get_videos(1)
})

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