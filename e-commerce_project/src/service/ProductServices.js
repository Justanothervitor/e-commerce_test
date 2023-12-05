// eslint-disable-next-line
import axios from "axios"

const PRODUCT_API = "http://localhost:8080/api/products/"
const GET_PRODUCTS_API = "http://localhost:8080/api/products/getproducts"

class ProductServices
{

    getAll()
    {
        return axios.get(GET_PRODUCTS_API);
    }

    getById(productId)
    {
        return axios.get(PRODUCT_API+productId);
    }
    
    insert(product)
    {
        return axios.post(PRODUCT_API,product);
    }

    update(product,productId)
    {
        return axios.put(PRODUCT_API+'/'+productId,product);
    }

    delete(productId)
    {
        return axios.delete(PRODUCT_API+'/'+productId);
    }
}
// eslint-disable-next-line
export default new ProductServices();