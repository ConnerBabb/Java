import axios from 'axios';

export default {

    addToFavorites(park) {
        return axios.post('/parks', park)
    }

}