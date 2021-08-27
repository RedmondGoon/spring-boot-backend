import axios from "axios";

export async function getUserProfile() {
    try {
        const res = await axios.get(
            "http://localhost:8080/holdings/Home?accId=1"
        );
        // const res = await axios.get("data/homeData.json");
        return res.data;
    } catch (err) {
        return err;
    }
}

export async function updateDeposit(cash) {
    try {
        const id = 1;
        const res = await axios.put(
            "http://localhost:8080/portfolio/cash/accId",
            null,
            {
                params: {
                    id,
                    cash,
                },
            },
            {
                Headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Access-Control-Allow-Origin": "*",
                },
            }
        );
        console.log(res);
        return true;
    } catch (err) {
        return err;
    }
}

export async function updateWithdrawal(negCash) {
    const cash = -negCash;
    try {
        const id = 1;
        const res = await axios.put(
            "http://localhost:8080/portfolio/cash/accId",
            null,
            {
                params: {
                    id,
                    cash,
                },
            },
            {
                Headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Access-Control-Allow-Origin": "*",
                },
            }
        );
        console.log(res);
        return true;
    } catch (err) {
        return err;
    }
}
