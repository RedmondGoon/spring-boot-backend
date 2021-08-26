export async function getSymbols() {
    const res = await fetch(
        "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" +
            process.env.REACT_APP_TICKER_API_KEY
    );
    const data = await res.text();
    const items = data.split("\n");
    return items.slice(1).map((item) => item.split(",")[0]);
}

export async function getFeed(symbol) {
    const res = await fetch(
        `https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=${symbol}&apikey=` +
            process.env.REACT_APP_TICKER_API_KEY
    );
    const data = await res.json();
    if ("Time Series (Daily)" in data) {
        const mostRecentDailyMeta = Object.entries(
            data["Time Series (Daily)"]
        )[0][1];
        const meta = Object.values(mostRecentDailyMeta);
        const open = meta[0];
        const high = meta[1];
        const low = meta[2];
        const close = meta[3];

        return {
            note: "",
            symbol,
            open,
            high,
            low,
            close,
        };
    } else {
        return {
            note: "Thank you for using Alpha Vantage! Our standard API call frequency is 5 calls per minute and 500 calls per day. Please visit https://www.alphavantage.co/premium/ if you would like to target a higher API call frequency.",
        };
    }
}
