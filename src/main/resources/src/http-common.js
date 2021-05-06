import axios from 'axios';

export default axios.create({
    baseURL : "http://localhost:9292",
    headers: {
        "Content-type":"application/json",
        'Accept': 'application/json'
    }
})