import axios from "axios";
import {store} from "@/scripts/store.js";

export const http = axios.create({
    baseURL: 'http://localhost:3000/foxtube',
    headers: {
        'Authorization': store.token,
    },
})
