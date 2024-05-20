<script setup>
import {ref} from "vue";

const props = defineProps({
  videos: {
    type: Array,
    required: true
  }
});

const videos_per_page = 8;
let page = ref(1)

</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col
          v-for="video in videos"
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
                :src="video.thumbnail"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="75%"
                cover
            >
            </v-img>

            <v-card-actions class="h-25">
              <v-card-title v-text="video.title"></v-card-title>
              <v-spacer></v-spacer>
              <v-card-text class="text--primary">{{ video.author }}</v-card-text>
            </v-card-actions>
          </v-card>
        </router-link>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="12" sm="8">
        <v-pagination
            v-model="page"
            :length="videos.length / videos_per_page + 1"
            rounded="circle"
            next-icon="mdi-menu-right"
            prev-icon="mdi-menu-left"
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