import React from "react";
import NavBar from "./StoreComponents/NavBar"
import MainPage from "./StoreComponents/MainPage"
import Footer from "./StoreComponents/Footer"

function AppStore()
{
    return(
        <div>
            <NavBar/>
            <MainPage/>
            <Footer/>
        </div>
    )
}

export default AppStore;