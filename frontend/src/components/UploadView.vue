<script setup>
import {ref} from "vue";
import fix from "@/assets/fix.png";

let title = ref('')
let cover = ref(null)
let description = ref('')
let video = ref(null)

let terms = ref(false)

let loading = ref(false)

const rules = {
  required: value => !!value || 'Required.',
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
            v-model="title"
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
            @input="(event)=>{cover=event.target.files[0]; console.log(cover)}"
        ></v-file-input>

        <v-file-input
            :rules="[rules.required]"
            color="primary"
            label="Video"
            accept="video/*"
            show-size
            prepend-icon="mdi-video"
            @input="(event)=>{video=event.target.files[0]}"
        ></v-file-input>

        <v-textarea
            v-model="description"
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
            @click=""
        >
          Upload
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<style scoped>

</style>