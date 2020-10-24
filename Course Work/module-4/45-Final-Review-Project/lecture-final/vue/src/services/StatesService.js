import axios from 'axios';

export default {

    getStates() {
        return axios.get('/states');
    }

}