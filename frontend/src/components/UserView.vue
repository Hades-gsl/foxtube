<script setup>
import AppBar from "@/components/AppBar.vue";
import VideoGrid from "@/components/VideoGrid.vue";
import {onMounted, ref} from "vue";
import {getHttp} from "@/scripts/http.js";
import {store} from "@/scripts/store.js";
// https://randomuser.me/api/

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
})


async function get_videos(page) {
  let res = await getHttp().get(`/video`,
      {
        params: {
          offset: (page - 1) * store.videos_per_count,
          author_id: props.id
        }
      }
  )

  if (res.status !== 200) {
    return []
  }

  return res.data
}

const videos = ref([])

onMounted(async () => {
  videos.value = await get_videos(1)
})

</script>

<template>
  <v-layout class="rounded rounded-md">
    <AppBar/>

    <v-main>
      <v-card
          class="mx-auto"
      >
        <v-img src='https://www.nju.edu.cn/images/ban9.jpg' cover height="250">
          <v-container class="pb-0 mb-0 ml-0 h-100" fluid>
            <div class="text-h4 pa-4 justify-center d-flex">User Profile</div>
            <v-layout row class="align-center">
              <v-avatar :image="store.user.avatar" size="x-large" class="mt-2 ml-2"></v-avatar>
              <div class="mb-0 pb-0">
                <v-card-title>{{ store.user.username }}</v-card-title>
                <v-card-subtitle>{{ store.user.email }}</v-card-subtitle>
                <v-divider></v-divider>
              </div>
            </v-layout>
            <v-card-text>{{ store.user.profile }}</v-card-text>
          </v-container>
        </v-img>
      </v-card>

      <v-card width="100%" class="my-3">
        <v-card-title class="text-h5 text-center">Your videos</v-card-title>
      </v-card>

      <VideoGrid :videos="videos" :video_count="store.user.video_count" :get_videos="get_videos"/>
    </v-main>
  </v-layout>
</template>

<style scoped>

</style>