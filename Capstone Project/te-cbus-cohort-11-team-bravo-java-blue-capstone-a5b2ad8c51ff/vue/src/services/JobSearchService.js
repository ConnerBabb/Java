import axios from 'axios';

const http = axios.create({
    baseURL: "https://cors-anywhere.herokuapp.com/https://jobs.github.com/positions.json"
})

export default {
    getJobs(searchLocation, searchDescription) {
        return http.get("/",{
            params: {
            description: searchDescription,
            location: searchLocation
        }, })
    }
}