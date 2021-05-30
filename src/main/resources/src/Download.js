import React from "react";
import Stockservice from "./Stockservice";
import "../src/App.css";
import "../src/ContactForm.css";

class Download extends React.Component {
  constructor(props) {
    super(props);
    this.onChangeSearch = this.onChangeSearch.bind(this);
    this.searchTitle = this.searchTitle.bind(this);
    this.state = {
      stockexchange: [],
      searchstockexchange: [],
      searchelem: "",
      searchTitle: "",
    };
  }

  onChangeSearch(e) {
    const searchTitle = e.target.value;
    this.setState({
      searchTitle: searchTitle,
    });
  }

  componentDidMount() {
    Stockservice.getStockService().then((response) => {
      this.setState({ stockexchange: response.data });
    });
  }

  searchTitle() {
    Stockservice.loadMongoService(this.state.searchTitle)
      .then((response) => {
        this.setState({ searchstockexchange: response.data });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { searchTitle } = this.state;
    return (
      <div>
        <div className="relative flex justify-center pt-12 lg:pt-30 px-8 py-10">
          <div className="rounded-lg shadow-2xl p-20 form">
            <label className="text-3xl text-white-100 font-bold cursive">
              Stock Report{" "}
            </label>

            <input
              placeholder="Enter date format (dd/mm/yyyy)"
              value={searchTitle}
              onChange={this.onChangeSearch}
            />
            <button
              type="submit"
              className="text-4xl text-white-100 cursive"
              onClick={this.searchTitle}
            >
              Download
            </button>
          </div>
        </div>

        <div className="Appx">
          <label className="mb-10 text-3xl text-white-100 font-bold cursive">
            The last 10 days data will be downloaded and stored directly into
            the database{" "}
          </label>
        </div>
      </div>
    );
  }
}

export default Download;
