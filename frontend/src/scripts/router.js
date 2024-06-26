import {createRouter, createWebHistory} from "vue-router";
import HomeView from "@/components/HomeView.vue";
import LoginView from "@/components/LoginView.vue";
import RegisterView from "@/components/RegisterView.vue";
import VideoPlayView from "@/components/VideoPlayView.vue";
import UploadView from "@/components/UploadView.vue";
import UserView from "@/components/UserView.vue";
import {store} from "@/scripts/store.js";
import {getHttp} from "@/scripts/http.js";

export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', component: HomeView, name: 'home'},
        {path: '/login', component: LoginView, name: 'login'},
        {path: '/register', component: RegisterView, name: 'register'},
        {path: '/video/:id', component: VideoPlayView, name: 'video', props: true},
        {
            path: '/upload',
            component: UploadView,
            name: 'upload',
            beforeEnter: (to, from) => {
                if (store.token == null || store.user == null) {
                    return {name: 'login'};
                }
            }
        },
        {
            path: '/user/:id',
            component: UserView,
            name: 'user',
            props: true,
            beforeEnter: (to, from) => {
                if (store.token == null || store.user == null) {
                    return {name: 'login'};
                }
            }
        },
        {path: '/:pathMatch(.*)*', redirect: '/'},
    ],
})

router.beforeEach((to, from) => {
    if (store.token === null) {
        let cookies = {}
        document.cookie.split(';').forEach(cookie => {
            let [name, value] = cookie.split('=').map(c => c.trim());
            cookies[name] = value;
        })
        store.token = cookies.token;
    }

    if (store.token !== undefined && store.user === null) {
        getHttp().get('/user').then(response => {
            store.user = response.data;
        }).catch(error => {
            console.log(error);
        })
    }
})