import React from "react";
import Stockservice from "./Stockservice";

class View extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stockexchange: [],
    };
  }

  componentDidMount() {
    Stockservice.getStockService().then((response) => {
      this.setState({ stockexchange: response.data });
    });
  }

  render() {
    return (
      <div className="Appx">
        <table className="table table-striped">
          <thead>
            <tr>
              {/* <td > Symbol </td>
                        <td> Series </td>
                        <td> Open </td>
                        <td> High </td>
                        <td> Low </td>
                        <td> Close </td>
                        <td> Last </td>
                        <td> Prevclose </td>
                        <td> Tottrdoty </td>
                        <td> Tottrdval </td>
                        <td> Timestamp </td>
                        <td> Totaltrades </td>
                        <td> Isin </td> */}
            </tr>
          </thead>
          <tbody>
            {this.state.stockexchange.map((se) => (
              <tr key={se.isin}>
                <td> {se.symbol} </td>
                <td> {se.series} </td>
                <td> {se.open} </td>
                <td> {se.high} </td>
                <td> {se.low} </td>
                <td> {se.close} </td>
                <td> {se.last} </td>
                <td> {se.prevclose} </td>
                <td> {se.tottrdoty} </td>
                <td> {se.tottrdval} </td>
                <td> {se.timestamp} </td>
                <td> {se.totaltrades} </td>
                <td> {se.isin} </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default View;
