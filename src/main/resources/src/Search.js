import React from 'react';
import Stockservice from './Stockservice';

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
        <div className="Apps">
            <div>
            <input
            type="text-color black"
            placeholder="Search by stock"
            value={searchTitle}
            onChange={this.onChangeSearch}
           />
           </div>
           <div>
            <button
            className = "btn btn-outline-secondary"
            type = "button"
            onClick={this.searchTitle}
            >Search</button>
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