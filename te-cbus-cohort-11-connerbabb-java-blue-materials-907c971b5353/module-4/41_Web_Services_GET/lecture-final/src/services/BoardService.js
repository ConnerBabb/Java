import axios from 'axios'

const http = axios.create( {
    baseURL: 'http://localhost:3000'
})

export default {

    getBoards() {
        return http.get('/boards');
    },

    getCards(boardId) {
        return http.get(`/boards/${boardId}`);
    },

    getCard(boardId, cardId) {
        return http.get(`/boards/${boardId}`)
            .then ( response => {
                const cards = response.data.cards;
                return cards.find( card => card.id == cardId );
        })
    }
}