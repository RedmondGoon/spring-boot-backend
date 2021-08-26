import { Line } from "react-chartjs-2";
import { Paper, Card, Typography } from "@material-ui/core";
import PropTypes from "prop-types";
import "../views/Stocks.css";


const options = {
    plugins: {
        legend: {
            display: false,
        },
    },
    scales: {
        y: {
            display: true,
            ticks: {
                display: true,
                color: "rgba(255, 255, 255, 1)",
                beginAtZero: true,
            },
            grid: {
                color: "rgba(255,255,255,0.5)",
            },
        },
        x: {
            display: true,
            ticks: {
                color: "#fff",
                display: true,
                beginAtZero: true,
            },
            grid: {
                color: "rgba(255,255,255,0.5)",
            },
        },
    },
};

export default function StockChart(props) {
    return (
        <div className="stock-holder">
            <Paper elevation={3}>
                <Card style={{ padding: "1rem" }} >
                    <Typography variant="h5">Historical Stock Prices</Typography>
                    <Line data={props.data} options={options} />
                </Card>
            </Paper>
        </div>
    );
}

StockChart.propTypes = {
    data: PropTypes.any,
}