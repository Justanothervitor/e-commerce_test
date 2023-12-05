import React from "react";
import "./App.css";
import {Routes,Route} from 'react-router-dom';
import AppStore from "./AppStore";
import LoginComponent from "./DomainComponents/LoginComponent";
import ProductViewComponent from "./DomainComponents/ProductViewComponent";

function App()
{   
    return(

            <div>
                <Routes>
                    <Route path="/" exact Component={AppStore}/>
                    <Route path="/login" exact Component={LoginComponent}/>
                    <Route path="/view/:id" exact Component={ProductViewComponent}/>
                </Routes>
            </div>

    )
}

export default App