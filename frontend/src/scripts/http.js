import axios from "axios";
import {store} from "@/scripts/store.js";

let http = null

export function getHttp() {
    if (http === null) {
        http = axios.create({
            baseURL: 'http://localhost:8000/foxtube',
        })

        http.interceptors.request.use(config => {
            if (store.token.value !== null) {
                config.headers.Authorization = `Bearer ${store.token.value}`
            }
            return config
        })

        http.interceptors.response.use(
            response => response,
            error => {
                if (error.response.status === 401) {
                    store.token.value = null
                    store.user.value = null
                }
                return Promise.reject(error)
            }
        )
    }

    return http
}