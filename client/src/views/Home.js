import {
    Card,
    CardContent,
    CardActions,
    Typography,
    Paper,
    Divider,
    Button,
    Dialog,
    DialogContent,
    DialogActions,
    DialogTitle,
    Slider,
    TextField,
} from "@material-ui/core";
import "./Home.css";
import PortfolioChart from "../ui/PortfolioChart";
import { deposit, withdraw } from "../service/portfolio";
import {
    getUserProfile,
    updateDeposit,
    updateWithdrawal,
} from "../service/home";
import { useState, useEffect } from "react";

export default function Home() {
    const [depositOpen, setDepositOpen] = useState(false);
    const [withdrawOpen, setWithdrawOpen] = useState(false);
    const [depositAmount, setDepositAmount] = useState(0);
    const [withdrawAmount, setWithdrawAmount] = useState(0);
    const [accountInfo, setAccountInfo] = useState();

    useEffect(() => {
        asyncGetUserProfile();
    }, []);

    const asyncGetUserProfile = async () => {
        let account = await getUserProfile();
        console.log(account);
        setAccountInfo(account);
    };

    const depositForClient = async () => {
        try {
            await updateDeposit(depositAmount);
            asyncGetUserProfile();
            setDepositOpen(false);
        } catch (err) {
            console.log(err);
        }
    };

    const withdrawForClient = async () => {
        try {
            await updateWithdrawal(withdrawAmount);
            asyncGetUserProfile();
            setWithdrawOpen(false);
        } catch (err) {
            console.log(err);
        }
    };

    const onDepositChange = (e) => {
        let value = e.target.value;
        if (!isNaN(value) && !isNaN(parseFloat(value))) {
            setDepositAmount(e.target.value);
        } else {
            console.log("must be number!");
        }
    };

    const onWithdrawChange = (_, value) => {
        setWithdrawAmount(value);
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
                                {accountInfo
                                    ? `S$${accountInfo.account}`
                                    : "S$0"}
                            </Typography>
                            <Divider />
                            <Divider />
                            <div className="info">
                                <Typography variant="h6" gutterBottom>
                                    Buying Power
                                </Typography>
                                <Typography variant="h6" gutterBottom>
                                    {accountInfo
                                        ? `S$${accountInfo.buyingPower}`
                                        : "S$0"}
                                </Typography>
                            </div>
                            <Divider />
                        </CardContent>
                        <CardActions>
                            <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => {
                                    setDepositOpen(true);
                                }}
                            >
                                Deposit
                            </Button>
                            <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => {
                                    setWithdrawOpen(true);
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
            <div>
                <Dialog
                    fullWidth
                    maxWidth="md"
                    open={depositOpen}
                    onClose={() => {
                        setDepositOpen(false);
                    }}
                >
                    <DialogTitle>Deposit</DialogTitle>
                    <DialogContent>
                        <TextField
                            variant="outlined"
                            color="secondary"
                            fullWidth
                            autoFocus
                            placeholder="Input deposit amount"
                            onChange={onDepositChange}
                            type="number"
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button
                            color="secondary"
                            variant="contained"
                            onClick={depositForClient}
                        >
                            Confirm Deposit
                        </Button>
                    </DialogActions>
                </Dialog>
                <Dialog
                    fullWidth
                    maxWidth="md"
                    open={withdrawOpen}
                    onClose={() => {
                        setWithdrawOpen(false);
                    }}
                >
                    <DialogTitle>Withdraw</DialogTitle>
                    <DialogContent>
                        <Slider
                            style={{ marginTop: 30 }}
                            color="secondary"
                            min={0}
                            max={
                                accountInfo
                                    ? Number(accountInfo.buyingPower)
                                    : 100
                            }
                            defaultValue={0.1}
                            step={1}
                            value={withdrawAmount}
                            valueLabelDisplay="auto"
                            onChangeCommitted={onWithdrawChange}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button
                            color="secondary"
                            variant="contained"
                            onClick={withdrawForClient}
                        >
                            Confirm Withdraw
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </div>
    );
}
