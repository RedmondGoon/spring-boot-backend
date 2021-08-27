import React, { useRef, useState } from 'react';
import {
    Typography,
    Container,
    Card,
    CardContent,
    Box,
    makeStyles,
    Grid,
    Button,
    TextField,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    Radio,
    RadioGroup,
    FormControlLabel,
    FormControl,
} from '@material-ui/core';
import StockCards from '../ui/StockCards';
import StockChart from '../ui/StockChart';
import {getData, getHistData, buyStock, sellStock, getCurrentPrice} from '../service/stocks';

const useStyles = makeStyles({
    root: {
        maxWidth: 300,
    },
    cardTitle: {
        fontSize: 12
    }
});


export default function Stocks() {
    
    const [data, setData] = useState();
    const [histData, setHistData] = useState();
    let tempRange;
    const [range, setRange] = useState();
    const [price, setPrice] = useState();
    const stock = useRef('');

    const [openBuy, setOpenBuy] = useState(false);
    const [openSell, setOpenSell] = useState(false);
    const qty = useRef(0);

    const handleRadioChange = (event) => {
        console.log(event.target.value);
        setRange(event.target.value);
    }
    

    const handleBuyOpen = () => {
        setOpenBuy(true);
    };
    const handleBuyClose = () => {
        setOpenBuy(false);
    };
    const handleSellOpen = () => {
        setOpenSell(true);
    };
    const handleSellClose = () => {
        setOpenSell(false);
    };

    return (
        <Container>
            <Box height={20} width={40} alignItems="center" justifyContent="center" />
            <TextField 
            id="outlined-basic" 
            label="Stock Ticker" 
            variant="outlined" 
            placeholder="Stock Name"
            inputRef={stock}            
            />
                <FormControl component="fieldset">
                    <RadioGroup aria-label="quiz" name="quiz" onChange={handleRadioChange}>
                        <FormControlLabel value="daily" control={<Radio />} label="Daily" />
                        <FormControlLabel value="weekly" control={<Radio />} label="Weekly" />
                        <FormControlLabel value="monthly" control={<Radio />} label="Monthly" />
                    </RadioGroup>
                </FormControl>
            <Button
            variant="contained"
            color="primary"
            size="small"
            onClick={ async () => {
                setData(await getData(stock.current.value));
                setHistData(await getHistData(stock.current.value, range));
                setPrice(await getCurrentPrice(stock.current.value));
            }
            }
            >
                Get Data
            </Button>
            <Box display="flex" flexDirection="row" p={1} m={1} bgcolor="background.paper">
                <Grid item xs={12} sm={6} md={6}>
                    <Card style={{ border: 'none', boxShadow: 'none' }}>
                        <CardContent align="left">
                            <Typography align="left" color="#eabf13" variant="h6" gutterBottom>
                                Current Stock Price
                            </Typography>
                            <Box height={40} width={40} alignItems="center" justifyContent="center">
                                {data == undefined ? (
                                    <Typography>
                                        {""}
                                    </Typography>                                                                       
                                ) : (
                                    <Typography>
                                        {price}
                                    </Typography>   
                                )}
                            </Box>
                        </CardContent>
                    </Card>
                    <div className="portfolio-chart">
                        {histData == undefined ? (
                            <StockChart data=""/>                                                                    
                        ) : (
                            <StockChart data={histData}/>  
                        )}
                    </div>
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Button variant="contained" color="primary" size="small" onClick={() => handleBuyOpen()}>
                        BUY
                    </Button>
                    <Dialog open={openBuy} onClose={() => handleBuyClose()} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Buy Stock</DialogTitle>
                        <DialogContent>
                        <DialogContentText>
                            Input the quantity of stock you wish to buy.
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="qty"
                            label="Stock Quantity"
                            fullWidth
                            inputRef={qty}
                        />
                        </DialogContent>
                        <DialogActions>
                        <Button onClick={() => handleBuyClose()} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={() => {                       
                            const res = buyStock(1, stock.current.value, qty.current.value, 1000);
                            handleBuyClose();
                            alert(res);
                        }} 
                        color="primary">
                            BUY
                        </Button>
                        </DialogActions>
                    </Dialog> 
                    <Button variant="contained" color="primary" size="small" onClick={() => handleSellOpen()}>
                        SELL
                    </Button>  
                    <Dialog open={openSell} onClose={() => handleSellClose()} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Sell Stock</DialogTitle>
                        <DialogContent>
                        <DialogContentText>
                            Input the quantity of stock you wish to sell.
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="qty"
                            label="Stock Quantity"
                            fullWidth
                            inputRef={qty}
                        />
                        </DialogContent>
                        <DialogActions>
                        <Button onClick={() => handleSellClose()} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={() => {                       
                            const res = sellStock(1, stock.current.value, qty.current.value, 1000);
                            handleSellClose();
                            alert(res);
                        }} 
                        color="primary">
                            SELL
                        </Button>
                        </DialogActions>
                    </Dialog>                   
                </Grid>
                <Box height={20} width={290} alignItems="center" justifyContent="center" />
                <Grid>
                {data == undefined ? (
                    <Container>
                        <StockCards title="Previous Day" key1="High" value1="" key2="Low" value2="" key3="Open" value3="" key4="Close" value4=""/>
                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                        <StockCards title="52 Week" key1="High" value1="" key2="Low" value2=""/>
                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                        <StockCards title="Volume" key1="Today" value1="" key2="Avg" value2=""/> 
                    </Container>                                                                 
                ) : (
                    <Container>
                        <StockCards title="Previous Day" key1="High" value1={data.previousday[0].high} key2="Low" value2={data.previousday[0].low} key3="Open" value3={data.previousday[0].open} key4="Close" value4={data.previousday[0].close} />
                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                        <StockCards title="52 Week" key1="High" value1={data.week52[0].high} key2="Low" value2={data.week52[0].low}/>
                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                        <StockCards title="Volume" key1="Today" value1={data.volume[0].today} key2="Avg" value2={data.volume[0].avg}/> 
                    </Container>
                )}
                    
                </Grid>
            </Box>
        </Container>
    );
}


