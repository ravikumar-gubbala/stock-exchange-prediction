import React from "react";
import logo1 from "../src/44.jpg";
import logo2 from "../src/22.jpg";
import stock4 from "../src/stock4.gif";
import stock1 from "../src/stock1.gif";
import stock2 from "../src/stock2.gif";
import stock3 from "../src/stock3.gif";

class Home extends React.Component {
  render() {
    return (
      <div>
        <div className="Appx">
          <div className="text-5xl pt-5 text-white-100 font-bold cursive">
            All about Stocks !!
            <div className="mt-10 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-12">
              <img src={stock3} alt="stock3" className="myImage" />

              <img src={logo1} alt="logo1" className="myImage" />
              <img src={stock2} alt="stock2" className="myImage" />
            </div>
            <div className="mt-10 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-12">
              <img src={stock4} alt="stock4" className="myImage" />
              <img src={logo2} alt="logo2" className="myImage" />
              <img src={stock1} alt="stock1" className="myImage" />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Home;
