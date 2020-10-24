import axios from 'axios';

const http = axios.create({
    baseURL: 'https://type.fit/api/quotes'
})
export default {
    motivateMe() {
        return http.get();
    }
}