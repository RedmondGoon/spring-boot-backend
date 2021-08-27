import axios from "axios";

export async function getData(stock) {
    console.log(stock);
    const data = {
        current: "130",
        previousday: [{ high: "150", low: "120", open: "130", close: "130" }],
        week52: [{ high: "150", low: "120" }],
        volume: [{ today: "1200", avg: "1300" }],
    };
    console.log(data);
    return data;
}

export async function getToday(stock) {
    let res;
    console.log("HERE")
    await axios
        .get(
            `https://65yrtzxkv2.execute-api.ap-southeast-1.amazonaws.com/SinglePrice?symbol=${stock}`
        )
        .then((response) => {
            console.log(response.data)
            res = response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    return res;
}

export async function getHistData(stock, range) {
    console.log(stock, range);
    let res;
    if (range == 'daily') {    
        await axios.get(`https://mn7q8pqe6b.execute-api.ap-southeast-1.amazonaws.com/Prod?symbol=${stock}`)
        .then((response) => {
            console.log(response.data);
            res = response.data;
        }).catch((error) => {
            console.log(error)
        });

    } else if (range == 'weekly') {
        await axios.get(`https://mn7q8pqe6b.execute-api.ap-southeast-1.amazonaws.com/Prod?symbol=${stock}`)
        .then((response) => {
            console.log(response.data);
            res = response.data;
        }).catch((error) => {
            console.log(error)
        });
    } else {
        await axios
            .get(
                `https://yrvmm9pj4l.execute-api.ap-southeast-1.amazonaws.com/Prod?symbol=${stock}`
            )
            .then((response) => {
                console.log(response.data);
                res = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
    }

    const data = {
        labels: Object.keys(res),
        datasets: [
            {
                label: "",
                data: Object.values(res),
                fill: false,
                backgroundColor: "rgba(202, 43, 81, 1)",
                borderColor: "rgba(202, 43, 81, 1)",
            },
        ],
    };
    return data;
}

export function buyStock(accId, ticker, quantity, execPrice) {
    console.log(accId, ticker, quantity, execPrice);
    return "bought!";
}

export function sellStock(accId, ticker, quantity, execPrice) {
    console.log(accId, ticker, quantity, execPrice);
    return "sold!";
}
