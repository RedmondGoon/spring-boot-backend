import { Line } from "react-chartjs-2";
import { Paper, Card, Typography } from "@material-ui/core";
import { getAccountChart } from "../service/portfolio.js";
import { useEffect, useState } from "react";

export default function PortfolioChart() {
    const [accountData, setAccountData] = useState([]);

    useEffect(() => {
        asyncGetAccountChart();
    }, []);

    const asyncGetAccountChart = async () => {
        const res = await getAccountChart();
        console.log(res);
        setAccountData(res);
    };

    const data = {
        datasets: [
            {
                label: "",
                data: accountData,
                fill: true,
                backgroundColor: "rgba(202, 43, 81, 1)",
                borderColor: "rgba(202, 43, 81, 1)",
            },
        ],
    };

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

    return (
        <div>
            <Paper elevation={3}>
                <Card style={{ padding: "1rem" }}>
                    <Typography variant="h5">Accounts</Typography>
                    <Line data={data} options={options} />
                </Card>
            </Paper>
        </div>
    );
}
