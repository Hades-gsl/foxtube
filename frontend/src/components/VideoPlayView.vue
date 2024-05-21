<script setup>
import {VideoPlayer} from "@videojs-player/vue";
import 'video.js/dist/video-js.css'
import AppBar from "@/components/AppBar.vue";
import {ref, reactive} from "vue";
import icon from '@/assets/icon.png'
import {getHttp} from "@/scripts/http.js";
import {store} from "@/scripts/store.js";

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

let video = reactive(null)

getHttp().get(`/video/${props.id}`).then(response => {
  video.value = response.data
}).catch(error => {
  console.error(error)
})

let page = ref(1)

let comments = reactive(null)

let comments_count = ref(0)

getHttp().get(`/comments/video/${props.id}/count`).then(response => {
  comments_count.value = response.data.count
}).catch(error => {
  console.error(error)
})

let isLike = ref(false)
let likeRecord = reactive(null)

getHttp().get('/like', {
  params: {
    video_id: props.id,
    author_id: store.user.id
  }
}).then(response => {
  if (response.status === 200) {
    isLike.value = true
    likeRecord = response.data
  }
}).catch(error => {
  console.error(error)
})

function favorite() {
  if (!isLike) {
    getHttp().post('/like', {
      video_id: props.id,
      author_id: store.user.id
    }).then(response => {
      console.log(response.data)
    }).catch(error => {
      console.error(error)
    })
  } else {
    getHttp().delete('/like', {
      params: {
        id: likeRecord.id,
        video_id: props.id,
        author_id: store.user.id
      }
    }).then(response => {
      console.log(response.data)
    }).catch(error => {
      console.error(error)
    })
  }

  isLike.value = !isLike.value
}

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
  src: video.src,
  poster: video.cover,
  controlBar: {
    timeDivider: true,
    durationDisplay: true,
    remainingTimeDisplay: true,
    fullscreenToggle: true
  }
})

function get_comments() {
  getHttp().get(`/comments/video/${props.id}`, {
    params: {
      offset: (page.value - 1) * store.comments_per_count
    }
  }).then(response => {
    comments.value = response.data
  }).catch(error => {
    console.error(error)
  })
}

get_comments()

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
            @click="favorite"
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
            :length="(comments_count + store.comments_per_count - 1) / store.comments_per_count"
            rounded="circle"
            next-icon="mdi-menu-right"
            prev-icon="mdi-menu-left"
            @prev="get_comments"
            @next="get_comments"
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