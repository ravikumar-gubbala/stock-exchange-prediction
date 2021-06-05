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
    High: 4000,
    Low: 2400,
  },
  {
    name: "Cycle 5",
    High: 3000,
    Low: 1398,
  },
  {
    name: "Cycle 7",
    High: 2000,
    Low: 9800,
  },
  {
    name: "Cycle 9",
    High: 2780,
    Low: 3908,
  },
  {
    name: "Cycle 11",
    High: 1890,
    Low: 4800,
  },
  {
    name: "Cycle 13",
    High: 2390,
    Low: 3800,
  },
  {
    name: "Cycle 15",
    High: 3490,
    Low: 4300,
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
