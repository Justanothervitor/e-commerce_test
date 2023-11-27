import React from "react";
import "./App.css";
import {Routes,Route} from 'react-router-dom';
import AppStore from "./AppStore";

function App()
{   
    return(

            <div>
                <Routes>
                    <Route path="/" exact Component={AppStore}/>
                </Routes>
            </div>

    )
}

export default App