
import axios from 'axios';

const PRODUCTS_API_REST_URL = "http://localhost:8080/products";

class APIService {
    
    getProducts(){
        return axios.get(PRODUCTS_API_REST_URL);
    }

}

export default new APIService();

