import axios from "axios";

const USERS_API = "http://localhost:8080/users"
const LOGIN_API = "http://localhost:8080/account"

class UserServices{

    getAll()
    {
        return axios.get(USERS_API)
    }

    login()
    {
        return axios.get(LOGIN_API)
    }
}

export default new UserServices();