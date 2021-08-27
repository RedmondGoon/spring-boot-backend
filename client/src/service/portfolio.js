import axios from "axios";

export async function deposit(clientID, amount) {}

export async function withdraw(clientID, amount) {}

export async function getAccountChart() {
    try {
        // const result = await axios.get("data/account.json");
        const result = await axios.get(`http://localhost:8080/chart?accId=1`);
        return result.data;
    } catch (err) {
        return err;
    }
}

export async function getPortfolioPerformanceChart() {
    try {
        const result = await axios.get("data/portfolioPerformance.json");
        return result.data;
    } catch (err) {
        return err;
    }
}

export async function getCompositionProfile() {
    try {
        const res = await axios.get(
            "http://localhost:8080/holdings/Donut?accId=1"
        );
        // const res = await axios.get("data/donutChart.json");
        return res.data;
    } catch (err) {
        return err;
    }
}
