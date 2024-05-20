<script setup>
import AppBar from "@/components/AppBar.vue";
import VideoGrid from "@/components/VideoGrid.vue";
import {useRoute} from "vue-router";
// https://randomuser.me/api/

const id = useRoute().params.id

let user = {
  id: 1,
  username: 'John Doe',
  email: 'doe@example.com',
  password: 'password',
  profile: 'This is a user.',
  avatar: 'https://randomuser.me/api/portraits/thumb/men/75.jpg'
}

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

    <v-main>
      <v-card
          class="mx-auto"
      >
        <v-img src='https://www.nju.edu.cn/images/ban9.jpg' cover height="250">
          <v-container class="pb-0 mb-0 ml-0 h-100" fluid>
            <div class="text-h4 pa-4 justify-center d-flex">User Profile</div>
            <v-layout row class="align-center">
              <v-avatar :image="user.avatar" size="x-large" class="mt-2 ml-2"></v-avatar>
              <div class="mb-0 pb-0">
                <v-card-title>{{ user.username }}</v-card-title>
                <v-card-subtitle>{{ user.email }}</v-card-subtitle>
                <v-divider></v-divider>
              </div>
            </v-layout>
            <v-card-text>{{ user.profile }}</v-card-text>
          </v-container>
        </v-img>
      </v-card>

      <v-card width="100%" class="my-3">
        <v-card-title class="text-h5 text-center">Your videos</v-card-title>
      </v-card>

      <VideoGrid :videos="videos"/>
    </v-main>
  </v-layout>
</template>

<style scoped>

</style>