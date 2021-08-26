import { Card } from "@material-ui/core";
import { Line } from "react-chartjs-2";
import { Doughnut } from "react-chartjs-2";
import { useState, useEffect } from "react";
import "./Portfolio.css";
import { getPortfolioPerformanceChart } from "../service/portfolio";

export default function Portfolio() {
    const [portfolioPerformance, setPortfolioPerformance] = useState([]);

    useEffect(() => {
        asyncGetPortfolioPerformanceChart();
    }, []);

    const asyncGetPortfolioPerformanceChart = async () => {
        const res = await getPortfolioPerformanceChart();
        setPortfolioPerformance(res);
    };

    const data = {
        labels: ["Stocks", "Cash"],
        datasets: [
            {
                data: [10000, 20000],
                backgroundColor: [
                    "rgba(255, 99, 132, 0.2)",
                    "rgba(54, 162, 235, 0.2)",
                    "rgba(255, 206, 86, 0.2)",
                    "rgba(75, 192, 192, 0.2)",
                    "rgba(153, 102, 255, 0.2)",
                    "rgba(255, 159, 64, 0.2)",
                ],
                borderColor: [
                    "rgba(255, 99, 132, 1)",
                    "rgba(54, 162, 235, 1)",
                    "rgba(255, 206, 86, 1)",
                    "rgba(75, 192, 192, 1)",
                    "rgba(153, 102, 255, 1)",
                    "rgba(255, 159, 64, 1)",
                ],
                borderWidth: 1,
            },
        ],
    };

    const options = {
        color: "rgba(255, 255, 255, 1)",
    };

    const performanceData = {
        datasets: [
            {
                label: "",
                data: portfolioPerformance,
                fill: true,
                backgroundColor: "rgba(202, 43, 81, 1)",
                borderColor: "rgba(202, 43, 81, 1)",
            },
        ],
    };

    const performanceOptions = {
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
        <div className="portfolio">
            <div className="portfolio-performance-holder">
                <Card>
                    <Line data={performanceData} options={performanceOptions} />
                </Card>
            </div>
            <div className="portfolio-allocation-holder">
                <Card>
                    <Doughnut data={data} options={options} />
                </Card>
            </div>
        </div>
    );
}
