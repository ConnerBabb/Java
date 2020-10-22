import axios from 'axios'

const http = axios.create( {
    baseURL: process.env.VUE_APP_NPS_API
})

export default {
    getParks(state) {
        return http.get(`/parks?stateCode=${state.toLowerCase()}&api_key=${process.env.VUE_APP_NPS_API_KEY}`);
    }
}

