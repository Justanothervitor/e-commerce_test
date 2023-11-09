import React from "react"
import "./App.css"
import NavBar from "./NavBar"
import MainPage from "./MainPage"
import Footer from "./Footer"
import ProductComponent from "./Components/ProductComponent"
function App()
{   
    return(
    <div className="container">
        <NavBar/>
        <MainPage/>
        <ProductComponent/>
        <Footer/>
    </div>
    )
}

export default App