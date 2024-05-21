<script setup>

import {ref} from "vue";
import fix from "@/assets/fix.png";
import {useRouter} from "vue-router";
import {getHttp} from "@/scripts/http.js";

let username = ref(null)
let email = ref(null)
let password = ref(null)
let profile = ref(null)
let terms = ref(false)

let show = ref(false)
const rules = {
  required: value => !!value || 'Required.',
  min: v => v.length >= 8 || 'Min 8 characters',
}

let text = ref('')
let loading = ref(false)

async function submit() {
  if (!username.value) {
    text.value = 'Username is required'
    return
  } else if (!email.value) {
    text.value = 'Email is required'
    return
  } else if (!password.value) {
    text.value = 'Password is required'
    return
  } else if (password.value.length < 8) {
    text.value = 'Password must be at least 8 characters'
    return
  }

  loading.value = true

  const response = await getHttp().post('/user', {
    username: username.value,
    email: email.value,
    password: password.value,
    profile: profile.value
  })

  loading.value = false

  if (response.status === 201) {
    alert('User registered successfully!')
    await useRouter().push({name: 'login'})
  } else {
    console.log(response.status)
    alert('Failed to register user, please try again.')
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
        class="mx-auto"
        max-width="400"
    >
      <v-card-title class="text-center">User Registration</v-card-title>
      <v-container>
        <v-text-field
            v-model="username"
            :rules="[rules.required]"
            color="primary"
            label="Username"
            variant="underlined"
            @input="text=''"
        ></v-text-field>

        <v-text-field
            v-model="email"
            :rules="[rules.required]"
            color="primary"
            label="Email"
            placeholder="xxxxx@example.com"
            variant="underlined"
            @input="text=''"
        ></v-text-field>

        <v-text-field
            v-model="password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.min]"
            :type="show ? 'text' : 'password'"
            hint="At least 8 characters"
            color="primary"
            label="Password"
            placeholder="Enter your password"
            variant="underlined"
            counter
            @click:append="show = !show"
            @input="text=''"
        ></v-text-field>

        <v-textarea
            v-model="profile"
            color="primary"
            label="Profile"
            variant="underlined"
        ></v-textarea>

        <v-checkbox
            v-model="terms"
            color="secondary"
            label="I agree to site terms and conditions"
        ></v-checkbox>
      </v-container>

      <v-divider></v-divider>

      <v-card-actions>
        <v-btn
            :disabled="!terms"
            :loading="loading"
            color="success"
            size="large"
            type="submit"
            variant="elevated"
            block
            @click="submit"
        >
          Register
        </v-btn>
      </v-card-actions>

      <v-alert :text="text" v-if="!!text"></v-alert>
    </v-card>
  </div>
</template>

<style scoped>

</style>