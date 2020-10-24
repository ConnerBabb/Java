import axios from 'axios';

const http = axios.create({
    baseURL: process.env.VUE_APP_REMOTE_API
})
export default {
    askQuestion(message) { 
        return http.post('/question', {
            question: message
        });
    }
    
}