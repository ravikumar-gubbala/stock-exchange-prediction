import React from "react";
import "./App.css"
import StockDataComponent from "./StockDataComponent";
import Prediction from "./Prediction";
import Analyse from "./Analyse";
import Search from "./Search";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import NavBar from "../src/NavBar";
import "../src/style.css";
import gsap from "gsap";

const tl = gsap.timeline({ defaults: { ease: "power1.out" } });
tl.to(".text", { y: "0%", duration: 1, stagger: 0.25 });
tl.to(".slider", { y: "-100%", duration: 1.5, delay: 0.5 });
tl.to(".intro", { y: "-100%", duration: 1 }, "-=1");
tl.fromTo("nav", { opacity: 0 }, { opacity: 1, duration: 1 });
tl.fromTo(".big-text", { opacity: 0 }, { opacity: 1, duration: 1 }, "-=1");

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