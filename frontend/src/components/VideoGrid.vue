<script setup>
import {onMounted, onUpdated, ref} from "vue";
import {getHttp} from "@/scripts/http.js";

const props = defineProps({
  videos: {
    type: Array,
    required: true
  },
  video_count: {
    type: Number,
    required: true
  },
  get_videos: {
    type: Function,
    required: true
  },
  users: {
    type: Array,
    required: false
  }
});

const videos_per_page = 12;
const page = ref(1)
let users = ref([])

function get_users() {
  for (let i = 0; i < props.videos.length; i++) {
    getHttp().get(`/user/${props.videos[i].author_id}`).then(res => {
      if (res.status === 200) {
        users.value.push(res.data)
      } else {
        users.value.push(null)
      }
    })
  }
}

onUpdated(() => {
  get_users()
})

</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col
          v-for="(video, index) in videos"
          :key="video.id"
          cols="12"
          sm="6"
          md="3"
      >
        <router-link :to="`/video/${video.id}`">
          <v-card
              hover
              class="aspect-ratio"
          >
            <v-img
                :src="video.cover"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="75%"
                cover
            >
            </v-img>

            <v-card-actions class="h-25">
              <v-card-title>{{ video.title }}</v-card-title>
              <v-spacer></v-spacer>
              <v-card-text v-if="users[index]!=null" class="text--primary">{{ users[index].username }}</v-card-text>
            </v-card-actions>
          </v-card>
        </router-link>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="12" sm="8">
        <v-pagination
            v-model="page"
            :length="Math.ceil(videos.length / videos_per_page)"
            rounded="circle"
            next-icon="mdi-menu-right"
            prev-icon="mdi-menu-left"
            @prev="get_videos(page)"
            @next="get_videos(page)"
        ></v-pagination>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.aspect-ratio {
  aspect-ratio: 16 / 11;
  height: 100%;
}
</style>