import {createRouter, createWebHistory} from "vue-router";
import HomeView from "@/components/HomeView.vue";
import LoginView from "@/components/LoginView.vue";
import RegisterView from "@/components/RegisterView.vue";
import VideoPlayView from "@/components/VideoPlayView.vue";
import UploadView from "@/components/UploadView.vue";
import UserView from "@/components/UserView.vue";

export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', component: HomeView},
        {path: '/login', component: LoginView},
        {path: '/register', component: RegisterView},
        {path: '/video/:id', component: VideoPlayView},
        {path: '/upload', component: UploadView},
        {path: '/user/:id', component: UserView},
        {path: '/:pathMatch(.*)*', redirect: '/'},
    ],
})
