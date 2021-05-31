import React from "react";
import Stockservice from "./Stockservice";
import "../src/App.css";
import "../src/ContactForm.css";
import exportFromJSON from "export-from-json";

const fileName = "exportfile";
const exportType = "txt";

class StockDataComponent extends React.Component {
  constructor(props) {
    super(props);
    this.onChangeSearch = this.onChangeSearch.bind(this);
    this.onChangeSearchDate = this.onChangeSearchDate.bind(this);
    this.searchTitle = this.searchTitle.bind(this);
    this.searchDate = this.searchTitle.bind(this);
    this.state = {
      stockexchange: [],
      searchstockexchange: [],
      searchelem: "",
      searchTitle: "",
      searchDate: "",
    };
  }

  ExportToExcel = () => {
    const { searchstockexchange } = this.state;
    console.log("Scan result Export", searchstockexchange);
    const data = JSON.stringify(searchstockexchange);
    exportFromJSON({ data, fileName, exportType });
  };

  onChangeSearch(e) {
    const searchTitle = e.target.value;
    this.setState({
      searchTitle: searchTitle,
    });
  }

  onChangeSearchDate(e) {
    const searchDate = e.target.value;
    this.setState({
      searchDate: searchDate,
    });
  }

  componentDidMount() {
    Stockservice.getStockService().then((response) => {
      this.setState({ stockexchange: response.data });
    });
  }

  searchTitle() {
    Stockservice.loadpredictionService(
      this.state.searchTitle,
      this.state.searchDate
    )
      .then((response) => {
        this.setState({ searchstockexchange: response.data });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { searchTitle, searchDate } = this.state;
    return (
      <div>
        <div className="relative flex justify-center pt-12 lg:pt-30 px-8 py-10">
          <div className="rounded-lg shadow-2xl p-20 form">
            <label className="text-3xl text-white-100 font-bold cursive">
              Prediction for Stock{" "}
            </label>

            <input
              placeholder="Enter the Stock Name"
              value={searchTitle}
              onChange={this.onChangeSearch}
            />

            <input
              placeholder="Enter date format (dd/mm/yyyy)"
              value={searchDate}
              onChange={this.onChangeSearchDate}
            />

            <button
              type="submit"
              className="text-4xl text-white-100 cursive"
              onClick={this.searchTitle}
            >
              Scan
            </button>
          </div>
        </div>

        <div className="Appx">
          <div>
            <button
              type="submit"
              className="text-4xl text-black cursive"
              onClick={this.ExportToExcel}
            >
              Export Data
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default StockDataComponent;
