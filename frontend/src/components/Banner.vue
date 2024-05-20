<script setup>

const indexes = [2, 3, 4, 5, 6, 7, 8, 9]

function img(idx) {
  return `https://www.nju.edu.cn/images/ban${idx}.jpg`
}


</script>

<template>
  <div>
    <v-carousel
        cycle
        height="400"
        show-arrows="hover"
    >
      <v-carousel-item
          v-for="idx in indexes"
          :key="idx"
          :src="img(idx)"
          cover
      ></v-carousel-item>
    </v-carousel>
  </div>
</template>

<style scoped>

</style>