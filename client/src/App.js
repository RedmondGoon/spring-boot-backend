import { BrowserRouter, Route, Switch } from "react-router-dom";
import "./App.css";
import Navbar from "./ui/Navbar.js";
import Home from "./views/Home";
import Portfolio from "./views/Portfolio";
import Stocks from "./views/Stocks";
import Trade from "./views/Trade";
import Login from "./views/Login";
import ScrollingTicker from "./ui/ScrollingTicker";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={Login} />
                    <div>
                        <Navbar />
                        <Route path="/home" component={Home} />
                        <Route path="/portfolio" component={Portfolio} />
                        <Route path="/stocks" component={Stocks} />
                        <Route path="/trades" component={Trade} />
                    </div>
                </Switch>
            </BrowserRouter>
            <ScrollingTicker />
        </div>
    );
}

export default App;
