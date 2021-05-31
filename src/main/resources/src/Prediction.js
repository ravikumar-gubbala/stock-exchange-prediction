import React from "react";
import LineChart from "../src/Linechart";

class Prediction extends React.Component {
  render() {
    return (
      <div className="Appx">
        <div>
          <label className="text-3xl text-white-100 font-bold cursive">
            Line Chart Analysis
          </label>
          <td className="relative flex justify-center pt-20">
            {" "}
            <LineChart />{" "}
          </td>
        </div>
      </div>
    );
  }
}

export default Prediction;
