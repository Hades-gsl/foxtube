<script setup>

import {VideoPlayer} from "@videojs-player/vue";
import 'video.js/dist/video-js.css'
import AppBar from "@/components/AppBar.vue";
import {ref, reactive} from "vue";
import {useRoute} from "vue-router";
import icon from '@/assets/icon.png'

const id = useRoute().params.id

const video = {
  id: 1, title: '视频1', thumbnail: 'https://www.dmoe.cc/random.php', author: '作者1',
  src: 'http://mirrors.standaloneinstaller.com/video-sample/Panasonic_HDC_TM_700_P_50i.mp4',
  description: 'This is a video',
}

let isLike = ref(false)

let comments = [
  {
    avatar: 'https://randomuser.me/api/portraits/men/1.jpg',
    username: 'John Doe',
    text: 'This is a great post!',
    isLiked: true,
  },
  {
    avatar: 'https://randomuser.me/api/portraits/women/2.jpg',
    username: 'Jane Smith',
    text: 'I totally agree!',
    isLiked: false,
  },
];

let page = ref(1)

const playerOptions = reactive({
  controls: true,
  volume: 0.6,
  playbackRates: [0.5, 1.0, 1.5, 2.0],
  autoplay: false,
  muted: false,
  loop: false,
  preload: 'auto',
  language: 'en-US',
  aspectRatio: '16:9',
  liveui: true,
  // fluid: true,
  src: video.src,
  poster: video.thumbnail,
  // width: document.documentElement.clientWidth,
  // height: document.documentElement.clientHeight * 0.8,
  // notSupportedMessage: '此视频暂无法播放，请稍后再试',
  controlBar: {
    timeDivider: true,
    durationDisplay: true,
    remainingTimeDisplay: true,
    fullscreenToggle: true
  }
})

</script>

<template>
  <v-layout class="rounded rounded-md">
    <AppBar/>

    <v-main>
      <div>
        <v-card class="my-2">
          <div class="text-h4 pa-2 ml-2">{{ video.title }}</div>
          <div class="text-caption pa-2 ml-2 bg-grey-lighten-4">
            <div class="text-grey">{{ video.author }}</div>
          </div>
        </v-card>
      </div>

      <div class="my-2 mx-12">
        <video-player
            class="vjs-big-play-centered"
            :playsinline="true"
            :options="playerOptions"
        />
      </div>

      <div>
        <v-fab
            class="ms-4"
            icon="mdi-thumb-up-outline"
            :color="isLike?'blue':'white'"
            location="top end"
            size="large"
            absolute
            @click="isLike=!isLike"
        ></v-fab>

        <v-card class="my-2">
          <div class="pa-2">
            <div class="text-grey">{{ video.description }}</div>
          </div>
        </v-card>
      </div>

      <v-container>
        <v-list>
          <h1 class="text-center">Comments</h1>

          <v-text-field
              label="comment"
              append-inner-icon="mdi-send"
              @click:append-inner=""
          >
            <template v-slot:prepend>
              <img :src="icon" alt="image icon"/>
            </template>
          </v-text-field>


          <v-list-item v-for="(comment, index) in comments"
                       :key="index"
                       lines="three"
                       :title="comment.username"
                       :subtitle="comment.text">
            <template v-slot:prepend>
              <v-icon>mdi-account</v-icon>
            </template>

            <template v-slot:append>
              <v-btn icon>
                <v-icon
                    :color="comment.isLiked ? 'red' : 'grey'"
                    @click="comment.isLiked = !comment.isLiked"
                >mdi-heart
                </v-icon>
              </v-btn>
            </template>
          </v-list-item>
        </v-list>
      </v-container>

      <div>
        <v-pagination
            v-model="page"
            :length="4"
            rounded="circle"
            next-icon="mdi-menu-right"
            prev-icon="mdi-menu-left"
        ></v-pagination>
      </div>
    </v-main>
  </v-layout>
</template>

<style scoped>
.video-js .vjs-time-control {
  display: block;
}

.video-js .vjs-remaining-time {
  display: none;
}

</style>