import axios from "axios";
import {store} from "@/scripts/store.js";

let http = null

export function getHttp() {
    if (http === null) {
        http = axios.create({
            baseURL: 'http://localhost:8080/',
        })

        http.interceptors.request.use(config => {
            if (store.token !== null && store.token !== undefined) {
                config.headers.Authorization = `Bearer ${store.token}`
            }
            return config
        })

        http.interceptors.response.use(
            response => response,
            error => {
                if (error.response.status === 401) {
                    store.token = null
                    store.user = null
                }
                return Promise.reject(error)
            }
        )
    }

    return http
}