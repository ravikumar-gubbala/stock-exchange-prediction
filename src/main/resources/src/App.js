import React from "react";
import "./App.css"
import StockDataComponent from "./StockDataComponent";
import Prediction from "./Prediction";
import Analyse from "./Analyse";
import Search from "./Search";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import NavBar from "../src/NavBar";


function App(){
    

  return(
    <body>
      <BrowserRouter>
      <h1 className="Title">Stock Exchange Prediction Analysis </h1>
      <div className = "BackgroundLayout"> 
      <NavBar/>
      <Switch>
      <Route component ={StockDataComponent} path='/' exact/>
      <Route component ={Search} path='/search'/> ;
      <Route component ={Prediction} path='/prediction/'/>
      <Route component ={Analyse} path='/analyse'/>
  </Switch>



      </div>
    <footer> Developed  by  Soumadeep ❤ </footer>
    </BrowserRouter>

    </body> 

    
  );
}

export default App;