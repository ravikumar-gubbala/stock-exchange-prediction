import React from 'react';
import Stockservice from './Stockservice';
import "../src/App.css"
import "../src/ContactForm.css";


class Search extends React.Component{
    constructor(props){
        super(props);
        this.onChangeSearch=this.onChangeSearch.bind(this);
        this.searchTitle=this.searchTitle.bind(this);
        this.state = {
            stockexchange:[],
            searchstockexchange:[],
            searchelem:"",
            searchTitle:""
        }
    }


    onChangeSearch(e){
        const searchTitle =e.target.value;
        this.setState({
            searchTitle:searchTitle
        });
    }

    componentDidMount(){
        Stockservice.getStockService().then((response) => {
            this.setState({stockexchange: response.data})
        });
    }

    searchTitle(){
        Stockservice.searchStockService(this.state.searchTitle).then((response) => {
            this.setState({searchstockexchange: response.data})
            console.log(response.data)
        }).catch(e=>{
            console.log(e);
        });
    }

render(){
    const {searchTitle} = this.state;
    return(
        <div>
        <div className="relative flex justify-center pt-12 lg:pt-30 px-8 py-10">
        <div className="rounded-lg shadow-2xl p-20 form">
        <label className="text-3xl text-white-100 font-bold cursive">Search for Stock </label>
        <input placeholder="Enter the Stock Name" value={searchTitle} onChange={this.onChangeSearch}/>
        <button type="submit" className="text-4xl text-white-100 cursive" onClick={this.searchTitle}>Scan</button>
        </div>
        </div>
            <div className="Appx">
            <table className ="table table-striped">
                {/* <thead>
                    <tr>
                        <td > Symbol </td>
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
                        <td> Isin </td>
                    </tr>
                </thead> */}
                <tbody>
                    {
                        this.state.searchstockexchange.map(
                            se =>
                            <tr key = {se.isin}>
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
                        )
                    }
                </tbody>
            </table>
        </div>
        </div>
    )
}

}

export default Search;