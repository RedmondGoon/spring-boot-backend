import { BrowserRouter, Route, Switch } from "react-router-dom";
import "./App.css";
import Navbar from "./ui/Navbar.js";
import Home from "./views/Home";
import Portfolio from "./views/Portfolio";
import Stocks from "./views/Stocks";
import Trade from "./views/Trade";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Navbar />
                <Switch>
                    <Route exact path="/" component={Home} />
                    <Route path="/portfolio" component={Portfolio} />
                    <Route path="/stocks" component={Stocks} />
                    <Route path="/trades" component={Trade} />
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
