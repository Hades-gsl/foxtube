import {reactive, ref} from "vue";


export const store = reactive({
    token: ref(null),
    user: reactive({
        id: ref(null),
        username: ref(null),
        email: ref(null),
        password: ref(null),
        avatar: ref(null),
        profile: ref(null),
        video_count: ref(null),
    }),
    videos_per_count: ref(12),
    comments_per_count: ref(10),
})