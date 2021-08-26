export function getData(stock) {
    console.log(stock)
    const data = {
    "current": "130",
    "previousday":[
        {"high":"150", "low":"120", "open":"130", "close":"130"},
    ],
    "week52":[
        {"high":"150", "low":"120"},
    ],
    "volume":[
        {"today":"1200", "avg":"1300"}
    ],
    }
    console.log(data);
    return data;
}

export function getHistData(stock) {
    const obj = {
        "Mar":"150",
        "Apr":"145",
        "May":"130",
        "Jun":"150",
        "Jul":"154",
        "Aug":"146",
        "Sept":"150",
        "Oct":"152",
        "Nov":"150",
        "Dec":"148",
        "Jan":"145",
        "Feb":"148",
        "Mar20":"150",
        "Apr20":"145",
        "May2":"130",
        "Jun2":"150",
        "Jul2":"154",
        "Aug2":"146",
        "Sept2":"150",
        "Oct2":"152",
    }
    const data = {
        labels: Object.keys(obj),
        datasets: [
            {
                label: "",
                data: Object.values(obj),
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