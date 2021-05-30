import React from "react";
import logo1 from "../src/11.jpg";
import logo2 from "../src/22.jpg";
import logo4 from "../src/44.jpg";
import logo5 from "../src/55.jpg";

class Home extends React.Component {
  render() {
    return (
      <div>
        <div className="Appx">
          <div className="text-5xl pt-5 text-white-100 font-bold cursive">
            All about Stocks !!
            <div className="mt-10 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-12">
              <img src={logo1} alt="logo1" className="myImage" />
              <img src={logo5} alt="logo5" className="myImage" />
            </div>
            <div className="mt-10 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-12">
              <img src={logo4} alt="logo4" className="myImage" />
              <img src={logo2} alt="logo2" className="myImage" />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Home;
