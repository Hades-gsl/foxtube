<script setup>
import {VideoPlayer} from "@videojs-player/vue";
import 'video.js/dist/video-js.css'
import AppBar from "@/components/AppBar.vue";
import {onMounted, reactive, ref} from "vue";
import icon from '@/assets/icon.png'
import {getHttp} from "@/scripts/http.js";
import {store} from "@/scripts/store.js";

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
});

const page = ref(1)

const video = ref({})
const author = ref({})
const comments = ref([])
const comment_authors = ref([])
const comments_count = ref(0)
const isLike = ref(false)
const likeRecord = ref({})
const comment = ref('')


async function get_video() {
  const res = await getHttp().get(`/video/${props.id}`)
  if (res.status === 200) {
    video.value = res.data
  }
}

async function get_author() {
  const res = await getHttp().get(`/user/${video.value.author_id}`)
  if (res.status === 200) {
    return res.data
  }
}

async function get_comments_count() {
  const res = await getHttp().get(`/comment/count`, {
    params: {
      video_id: props.id
    }
  })

  if (res.status === 200) {
    comments_count.value = res.data.count
  }
}

async function get_like_record() {
  try {
    const res = await getHttp().get('/like', {
      params: {
        video_id: props.id,
        user_id: store.user.id
      }
    })

    if (res.status === 200) {
      isLike.value = true
      likeRecord.value = res.data
    }
  } catch (e) {
    console.log(e)
  }
}


function favorite() {
  if (store.user == null) {
    alert('Please login first!')
    return
  }

  if (!isLike.value) {
    getHttp().post('/like', {
      video_id: props.id,
      user_id: store.user.id
    }).then(response => {
      console.log(response.data)
    }).catch(error => {
      console.error(error)
    })
  } else {
    getHttp().delete(`/like/${likeRecord.value.id}`).then(response => {
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
  controlBar: {
    timeDivider: true,
    durationDisplay: true,
    remainingTimeDisplay: true,
    fullscreenToggle: true
  }
})

function get_comments() {
  getHttp().get(`/comment`, {
    params: {
      offset: (page.value - 1) * store.comments_per_count,
      video_id: props.id
    }
  }).then(response => {
    comments.value = response.data

    for (let i = 0; i < comments.value.length; i++) {
      getHttp().get(`/user/${comments.value[i].author_id}`).then(response => {
        comment_authors.value.push(response.data)
      }).catch(error => {
        console.error(error)
      })
    }
  }).catch(error => {
    console.error(error)
  })
}


function submit_comment() {
  if (store.token === null) {
    alert('Please login first!')
    return
  }

  if (comment.value === null || comment.value === '') {
    alert('Comment cannot be empty!')
    return
  }

  getHttp().post('/comment', {
    content: comment.value,
    video_id: props.id,
    author_id: store.user.id
  }).then(response => {
    console.log(response.data)

    alert('Comment successfully!')

    comment.value = ''
    get_comments()
  }).catch(error => {
    console.error(error)
  })
}

onMounted(async () => {
  await get_video()

  if (video.value != null) {
    author.value = await get_author()
  }

  await get_comments_count()

  if (store.user != null) {
    await get_like_record()
  }

  get_comments()
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
            <div class="text-grey">{{ author.username }}</div>
          </div>
        </v-card>
      </div>

      <div class="my-2 mx-12">
        <video-player
            class="vjs-big-play-centered"
            :src="video.url"
            :poster="video.cover"
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
              v-model="comment"
              label="comment"
              append-inner-icon="mdi-send"
              clearable
              @click:append-inner="submit_comment"
          >
            <template v-slot:prepend>
              <v-avatar>
                <img v-if="store.user!=null" :src="store.user.avatar" :width="48" alt="image icon"/>
                <img v-else :src="icon" alt="image icon"/>
              </v-avatar>
            </template>
          </v-text-field>


          <v-list-item v-for="(comment, index) in comments"
                       :key="index"
                       lines="three"
                       :title="comment.username"
                       :subtitle="comment_authors[index].username">
            <p>{{comment.content}}</p>
            <template v-slot:prepend>
              <v-avatar :image="comment_authors[index].avatar"></v-avatar>
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
            :length="Math.ceil(comments_count / store.comments_per_count)"
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