import { Line } from "react-chartjs-2";
import { Paper, Card, Typography } from "@material-ui/core";

const data = {
    labels: ["1", "2", "3", "4", "5", "6"],
    datasets: [
        {
            label: "",
            data: [12, 19, 3, 5, 2, 3],
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

export default function PortfolioChart() {
    return (
        <div>
            <Paper elevation={3}>
                <Card style={{ padding: "1rem" }}>
                    <Typography variant="h5">Portfolio Chart</Typography>
                    <Line data={data} options={options} />
                </Card>
            </Paper>
        </div>
    );
}
