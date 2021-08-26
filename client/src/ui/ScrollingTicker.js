import Ticker from "react-ticker";
import "./ScrollingTicker.css";
import { useState, useEffect } from "react";

export default function ScrollingTicker() {
    const [symbols, setSymbols] = useState([]);

    useEffect(() => {
        console.log(process.env.REACT_APP_TICKER_API_KEY);
        fetch(
            "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" +
                process.env.REACT_APP_TICKER_API_KEY
        )
            .then((res) => res.text())
            .then((data) => {
                const items = data.split("\n");
                const tickerSymbols = items
                    .slice(1)
                    .map((item) => item.split(",")[0]);
                setSymbols(tickerSymbols);
            });
    }, []);

    const GetFeedData = () => {
        const [feed, setFeed] = useState();

        useEffect(() => {
            let mount = true;
            const getFeed = async () => {
                await fetch(
                    "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey=" +
                        process.env.REACT_APP_TICKER_API_KEY
                )
                    .then((res) => {
                        res.text();
                    })
                    .then((data) => {
                        const xmlDoc = new DOMParser().parseFromString(
                            data,
                            "text/xml"
                        );
                        const items = Array.from(
                            xmlDoc.querySelectorAll("item")
                        ).map((item) => ({
                            title: item.querySelector("title").textContent,
                            link: item.querySelector("link").textContent,
                        }));
                        if (mount) setFeed(items);
                    });
            };
            getFeed();

            return () => {
                mount = false;
            };
        }, []);

        return feed ? (
            <p>
                {feed.map((item) => (
                    <a key={item.title} href={item.link || ""}>
                        {item.title}
                    </a>
                ))}
            </p>
        ) : (
            <p style={{ marginLeft: 20, marginRight: 20 }}>
                <span style={{ color: "rgba(202, 43, 81, 1)" }}>
                    Waiting For Ticker Feed...
                </span>
            </p>
        );
    };

    return (
        <div className="ticker-wrapper">
            <Ticker>{() => <GetFeedData />}</Ticker>
        </div>
    );
}
