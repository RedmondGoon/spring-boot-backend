import "./ScrollingTicker.css";
import Ticker from "react-ticker";
import { useState, useEffect } from "react";
import { getSymbols, getFeed } from "../service/ticker";

export default function ScrollingTicker() {
    const [feed, setFeed] = useState([]);

    useEffect(() => {
        asyncStartFeed();
        setInterval(() => {
            asyncStartFeed();
        }, 1 * 60 * 1000);
    }, []);

    const asyncStartFeed = async () => {
        const _symbols = await getSymbols();
        let feeds = [];
        for (let i = 0; i < 4; i++) {
            const random = Math.floor(Math.random() * _symbols.length);
            const _feed = await getFeed(_symbols[random]);
            feeds.push(_feed);
        }
        setFeed(feeds);
    };

    const GetTickerFeed = () => {
        return (
            <p
                style={{
                    verticalAlign: "middle",
                    whiteSpace: "nowrap",
                    marginLeft: "3rem",
                }}
            >
                {feed.map((item) =>
                    item.note === "" ? (
                        <span key={item.symbol} style={{ color: "#fff" }}>
                            <b>{item.symbol}</b> OPEN: {item.open} CLOSE:{" "}
                            {item.close} HIGH: {item.high} LOW: {item.low}
                        </span>
                    ) : (
                        <span style={{ color: "rgba(202, 43, 81, 1)" }}>
                            {item.note}
                        </span>
                    )
                )}
            </p>
        );
    };

    return (
        <div className="ticker-wrapper">
            <Ticker>{() => <GetTickerFeed />}</Ticker>
        </div>
    );
}
