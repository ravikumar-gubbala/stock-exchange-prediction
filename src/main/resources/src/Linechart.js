import "../src/Styles.css";
import React from "react";
import searchstockexchange from "../src/StockDataComponent";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

const data = JSON.stringify(searchstockexchange);

const data1 = [
  {
    name: "Cycle 3",
    High: 2060.2,
    Low: 1959.6,
  },
  {
    name: "Cycle 5",
    High: 2065.51,
    Low: 1954.29,
  },
  {
    name: "Cycle 7",
    High: 2070.35,
    Low: 1949.45,
  },
  {
    name: "Cycle 9",
    High: 2038.94,
    Low: 1980.86,
  },
  {
    name: "Cycle 11",
    High: 2074.83,
    Low: 1944.97,
  },
  {
    name: "Cycle 13",
    High: 2047.39,
    Low: 1972.41,
  },
  {
    name: "Cycle 15",
    High: 2079.03,
    Low: 1940.77,
  },
];
console.log("Data is ", data);

export default function App() {
  return (
    <LineChart
      width={1200}
      height={400}
      data={data1}
      margin={{
        top: 1,
        right: 30,
        left: 20,
        bottom: 1,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" stroke="#82ca9d" />
      <YAxis stroke="#82ca9d" />
      <Tooltip stroke="#82ca9d" />
      <Legend />
      <Line
        type="monotone"
        dataKey="Low"
        stroke="#F08080"
        activeDot={{ r: 9 }}
      />
      <Line type="monotone" dataKey="High" stroke="#82ca9d" />
    </LineChart>
  );
}
