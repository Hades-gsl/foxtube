<script setup>
import icon from "@/assets/fix.png";
import {store} from "@/scripts/store.js";

function search() {
  alert("Sorry, this feature is currently not supported!")
}

function logout() {
  store.token = null
  store.user = null
  alert("Logout successfully!")
}

</script>

<template>
  <v-app-bar :elevation="2">
    <template v-slot:prepend>
      <router-link to="/">
        <img
            :src="icon"
            alt="FoxTube"
            class="ml-4"
            style="max-height: 100%;"
        >
      </router-link>
    </template>
    <template v-slot:append>

      <router-link v-if="!!store.user" :to="`/user/${store.user.id}`">
        <v-avatar
            v-if="!!store.user"
            :image="store.user.avatar"
            size="small"
            class="mr-4"
        ></v-avatar>
      </router-link>

      <router-link to="/upload">
        <v-btn icon>
          <v-icon>mdi-upload</v-icon>
          <v-tooltip
              activator="parent"
              location="bottom"
          >
            upload video
          </v-tooltip>
        </v-btn>
      </router-link>

      <router-link to="">
        <v-btn
            icon
            @click="search"
        >
          <v-icon>mdi-magnify</v-icon>
          <v-tooltip
              activator="parent"
              location="bottom"
          >
            search
          </v-tooltip>
        </v-btn>
      </router-link>

      <router-link to="/login">
        <v-btn
            icon
            v-if="!store.token"
        >
          <v-icon>mdi-login</v-icon>
          <v-tooltip
              activator="parent"
              location="bottom"
          >
            login
          </v-tooltip>
        </v-btn>
      </router-link>

      <router-link to="/">
        <v-btn
            icon
            v-if="!!store.token"
            @click="logout"
        >
          <v-icon>mdi-logout</v-icon>
          <v-tooltip
              activator="parent"
              location="bottom"
          >
            logout
          </v-tooltip>
        </v-btn>
      </router-link>
    </template>
  </v-app-bar>
</template>

<style scoped>

</style>