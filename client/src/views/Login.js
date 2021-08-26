import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { useState } from "react";
import userLogin from "../service/userLogin";
import { useHistory } from "react-router";
import "./Login.css";
import { Typography, Paper, Divider } from "@material-ui/core";

export default function Login() {
    const history = useHistory();

    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);

    const handleLogin = () => {
        const res = userLogin(email, password);
        if (res) {
            history.push("/home");
        }
    };

    return (
        <div
            style={{
                width: "100%",
                height: "100%",
                backgroundColor: "#424242",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}
        >
            <Paper elevation={5}>
                <div className="overall-container">
                    <div
                        style={{
                            padding: "10px",
                            fontVariant: "all-petite-caps",
                            fontWeight: "100",
                        }}
                    >
                        <Typography variant="h2">Sign In</Typography>
                    </div>
                    <Divider />
                    <br />
                    <div className="input-field">
                        <TextField
                            id="outlined-basic"
                            label="Email"
                            variant="outlined"
                            value={email}
                            color="secondary"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </div>
                    <div className="input-field">
                        <TextField
                            id="outlined-basic"
                            label="Password"
                            variant="outlined"
                            value={password}
                            color="secondary"
                            onChange={(event) => {
                                setPassword(event.target.value);
                            }}
                        />
                    </div>
                    <div className="login-button">
                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={handleLogin}
                        >
                            Login
                        </Button>
                    </div>
                </div>
            </Paper>
        </div>
    );
}
