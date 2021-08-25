import {
    Card,
    CardContent,
    CardActions,
    Typography,
    Paper,
    Divider,
    Button,
} from "@material-ui/core";
import "./Home.css";
import PortfolioChart from "../ui/PortfolioChart";
import { deposit, withdraw } from "../service/portfolio";

export default function Home() {
    const depositForClient = async (clientID, amount) => {
        try {
            await deposit(clientID, amount);
        } catch (err) {
            console.log(err);
        }
    };

    const withdrawForClient = async (clientID, amount) => {
        try {
            await withdraw(clientID, amount);
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <div className="home">
            <div className="portfolio-holder">
                <Paper elevation={3}>
                    <Card>
                        <CardContent>
                            <Typography variant="h5" gutterBottom>
                                Your Portfolio
                            </Typography>
                            <Divider />
                            <Typography variant="h6" gutterBottom>
                                Account
                            </Typography>
                            <Typography variant="subtitle1" gutterBottom>
                                S$1000
                            </Typography>
                            <Divider />
                            <br />
                            <div className="info">
                                <Typography variant="h6" gutterBottom>
                                    Settled Cash
                                </Typography>
                                <Typography variant="h6" gutterBottom>
                                    S$200
                                </Typography>
                            </div>
                            <Divider />
                            <div className="info">
                                <Typography variant="h6" gutterBottom>
                                    Buying Power
                                </Typography>
                                <Typography variant="h6" gutterBottom>
                                    S$300
                                </Typography>
                            </div>
                            <Divider />
                        </CardContent>
                        <CardActions>
                            <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => {
                                    depositForClient("clientID", 0);
                                }}
                            >
                                Deposit
                            </Button>
                            <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => {
                                    withdrawForClient("clientID", 0);
                                }}
                            >
                                Withdraw
                            </Button>
                        </CardActions>
                    </Card>
                </Paper>
            </div>
            <div className="portfolio-chart">
                <PortfolioChart />
            </div>
        </div>
    );
}
