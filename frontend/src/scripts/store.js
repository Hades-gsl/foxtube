import {reactive, ref} from "vue";

const cookies = document.cookie.split(';').reduce((cookies, cookie) => {
    const [name, value] = cookie.split('=').map(c => c.trim());
    cookies[name] = value;
    return cookies;
})


export const store = reactive({
    token: ref(cookies['token']),
    user: reactive({
        id: ref(null),
        username: ref(null),
        email: ref(null),
        password: ref(null),
        avatar: ref('https://randomuser.me/api/portraits/thumb/men/75.jpg'),
        profile: ref(null),
        videoCount: ref(null),
    }),
})