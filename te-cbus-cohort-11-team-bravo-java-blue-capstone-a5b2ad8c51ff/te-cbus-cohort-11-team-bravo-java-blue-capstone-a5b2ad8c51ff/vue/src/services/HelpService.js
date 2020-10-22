import axios from 'axios';

const http = axios.create({
    baseURL: process.env.VUE_APP_REMOTE_API
})
export default {

  getCommands() {
      return http.post('/chat', {
          data: {
              'quesiton': "1"
          }
      });
  }

}