import axios from "axios";

const LOGIN_API = "http://localhost:8080/auth/login"
const SIGNUP_API = "http//localhost:8080/auth/signup"
const DASHBOARD_API = "http//localhost:8080/dashboard"

class UserServices{

    login()
    {
        return axios.post(LOGIN_API)
    }

    signup()
    {
        axios.get(SIGNUP_API)
        return axios.post(SIGNUP_API)
    }

    dashboard()
    {
        return axios.get(DASHBOARD_API)
    }

}

export default new UserServices();