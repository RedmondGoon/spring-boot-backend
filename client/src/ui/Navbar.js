import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import { useState } from "react";
import { useHistory } from "react-router-dom";

export default function Navbar() {
    const history = useHistory();
    const [value, setValue] = useState("home");

    const switchRoute = (event, newValue) => {
        event.preventDefault();
        setValue(newValue);
        switch (newValue) {
            case "home":
                history.push("/");
                break;
            case "portfolio":
                history.push("/portfolio");
                break;
            case "trades":
                history.push("/trades");
                break;
            case "stocks":
                history.push("/stocks");
                break;
            default:
                history.push("/");
                break;
        }
    };

    return (
        <div>
            <AppBar position="static">
                <Tabs
                    value={value}
                    aria-label="simple tabs example"
                    onChange={switchRoute}
                >
                    <Tab label="HOME" value="home" />
                    <Tab label="PORTFOLIO" value="portfolio" />
                    <Tab label="STOCKS" value="stocks" />
                    <Tab label="TRADES" value="trades" />
                </Tabs>
            </AppBar>
        </div>
    );
}
