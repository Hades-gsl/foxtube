<script setup>

import {ref} from "vue";
import fix from "@/assets/fix.png";
import {getHttp} from "@/scripts/http.js";
import {useRouter} from "vue-router";
import {store} from "@/scripts/store.js";

let visible = ref(false)
let loading = ref(false)

let email = ref(null)
let password = ref(null)

async function submit() {
  if (!email.value || !password.value) {
    return
  }

  loading.value = true

  const response = await getHttp().get('/login', {
    params: {
      email: email.value,
      password: password.value
    }
  })

  loading.value = false

  if (response.status === 201) {
    alert('User logged in successfully!')
    store.user = response.data
    await useRouter().push({name: 'home'})
  } else {
    console.log(response.status)
    alert('Failed to login user, please try again.')
  }
}
</script>

<template>
  <div>
    <v-img
        class="mx-auto my-6"
        max-width="228"
        :src="fix"
    ></v-img>

    <v-card
        class="mx-auto pa-12 pb-8"
        elevation="8"
        max-width="448"
        rounded="lg"
    >
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field
          v-model="email"
          density="compact"
          placeholder="Email address"
          prepend-inner-icon="mdi-email-outline"
          variant="outlined"
      ></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password

        <a
            class="text-caption text-decoration-none text-blue"
            href="#"
            rel="noopener noreferrer"
            target="_blank"
        >
          Forgot login password?</a>
      </div>

      <v-text-field
          v-model="password"
          :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
          :type="visible ? 'text' : 'password'"
          density="compact"
          placeholder="Enter your password"
          prepend-inner-icon="mdi-lock-outline"
          variant="outlined"
          @click:append-inner="visible = !visible"
      ></v-text-field>

      <v-card
          class="mb-12"
          color="surface-variant"
          variant="tonal"
      >
        <v-card-text class="text-medium-emphasis text-caption">
          Warning: After 3 consecutive failed login attempts, you account will be temporarily locked for three hours. If
          you must login now, you can also click "Forgot login password?" below to reset the login password.
        </v-card-text>
      </v-card>

      <v-btn
          class="mb-8"
          color="blue"
          size="large"
          variant="tonal"
          block
          :loading="loading"
          @click="submit"
      >
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <router-link
            class="text-blue text-decoration-none"
            to="/register"
        >
          Sign up now
          <v-icon icon="mdi-chevron-right"></v-icon>
        </router-link>
      </v-card-text>
    </v-card>
  </div>
</template>
<style scoped>

</style>