import React, {useEffect,useState}from "react";
import "./App.css"

function  StockComponent(props) {

useEffect(()=>{
    getTweets()
});


    const [message,setMessage] = useState();


    const getTweets= async () => {
        const response = await fetch ('http://localhost:9292/StockExchange', {
            headers : { 
              'Content-Type': 'application/json',
              'Accept': 'application/json'
             }
      
          });
        console.log(response);
        const dataJson = await response.json();
        console.log(dataJson);
        setMessage(dataJson)
    }
        return(
        
            <body>
            <div className = "Tweet">
                {message}
            console.log(message);
            </div>
            </body>
        )
        }  

export default StockComponent;
