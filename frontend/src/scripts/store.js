import {reactive} from "vue";


export const store = reactive({
    token: null,
    user: null,
    videos_per_count: 12,
    comments_per_count: 10,
})