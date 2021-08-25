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

export default function Home() {
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
                            <div className="info">
                                <Typography variant="h6" gutterBottom>
                                    Dividence
                                </Typography>
                                <Typography variant="h6" gutterBottom>
                                    S$500
                                </Typography>
                            </div>
                            <Divider />
                        </CardContent>
                        <CardActions>
                            <Button variant="contained" color="secondary">
                                Deposit
                            </Button>
                            <Button variant="contained" color="secondary">
                                Withdraw
                            </Button>
                        </CardActions>
                    </Card>
                </Paper>
            </div>
            <div className="portfolio-chart">Portfolio Chart</div>
        </div>
    );
}
