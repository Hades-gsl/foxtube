<script setup>
import {reactive, ref} from "vue";
import fix from "@/assets/fix.png";
import {store} from "@/scripts/store.js";
import {getHttp} from "@/scripts/http.js";
import {useRouter} from "vue-router";


let video = reactive({
  title: null,
  cover: null,
  src: null,
  description: null,
  author_id: store.user.id
})

let terms = ref(false)
let loading = ref(false)

const rules = {
  required: value => !!value || 'Required.',
}

async function submit() {
  if (!video.title || !video.cover || !video.src || !video.description) {
    return
  }

  loading.value = true


  const formData = new FormData()
  formData.append('title', video.title)
  formData.append('cover', video.cover)
  formData.append('video', video.src)
  formData.append('description', video.description)
  formData.append('author_id', video.author_id)

  const response = await getHttp().post('/video', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

  loading.value = false

  if (response.status === 201) {
    alert('Video uploaded successfully!')
    await useRouter().push({name: 'home'})
  } else {
    console.log(response.status)
    alert('Failed to upload video, please try again.')
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
        max-width="450"
    >
      <v-card-title class="text-center">Upload Your Video</v-card-title>
      <v-container>
        <v-text-field
            v-model="video.title"
            :rules="[rules.required]"
            color="primary"
            label="Title"
        ></v-text-field>

        <v-file-input
            :rules="[rules.required]"
            color="primary"
            label="Cover"
            accept="image/*"
            show-size
            prepend-icon="mdi-image"
            @input="(event)=>{video.cover=event.target.files[0]}"
        ></v-file-input>

        <v-file-input
            :rules="[rules.required]"
            color="primary"
            label="Video"
            accept="video/*"
            show-size
            prepend-icon="mdi-video"
            @input="(event)=>{video.src=event.target.files[0]}"
        ></v-file-input>

        <v-textarea
            v-model="video.description"
            color="primary"
            label="Description"
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
            color="cyan"
            size="large"
            type="submit"
            variant="elevated"
            block
            @click="submit"
        >
          Upload
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<style scoped>

</style>