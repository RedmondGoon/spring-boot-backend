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
    TextField
} from '@material-ui/core';
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer
} from 'recharts';

const useStyles = makeStyles({
    root: {
        maxWidth: 300,
    },
    cardTitle: {
        fontSize: 12
    }
});


export default function Stocks() {

    const histData = [
        {
            date: 'Apr',
            price: '100'

        },
        {
            date: 'Mar',
            price: '120'

        },
        {
            date: 'Jun',
            price: '130'

        },
        {
            date: 'Jul',
            price: '120'

        },
    ];
    
    const [price, setPrice] = useState();
    const stock = useRef('');

    const getData = () => {
        console.log(stock.current.value)
        setPrice(150)
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
            <Button
            variant="contained"
            color="primary"
            size="small"
            onClick={getData}
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
                                <Typography align="center" variant="body3" gutterBottom>
                                    {price}
                                </Typography>
                            </Box>
                        </CardContent>
                    </Card>
                    <ResponsiveContainer  width={300} height={150}>
                        <div>
                            <LineChart
                                width={350}
                                height={200}
                                data={histData}
                                margin={{
                                    top: 1,
                                    right: 3,
                                    left: 2,
                                    bottom: 10
                                }}
                            >
                                <CartesianGrid strokeDasharray="3 3" />
                                <XAxis
                                    dataKey="date"
                                    label="Date"
                                />
                                <YAxis
                                    dataKey="price"
                                    label="Price"
                                />
                                <Tooltip />
                                <Line
                                    type="monotone"
                                    dataKey="price"
                                    stroke="#8884d8"
                                    activeDot={{ r: 8 }}
                                />
                            </LineChart>
                        </div>
                    </ResponsiveContainer>
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Box height={20} width={40} alignItems="center" justifyContent="center" />
                    <Button
                        variant="contained"
                        color="primary"
                        size="small"
                    >
                        BUY
                    </Button> 
                    <Button
                        variant="contained"
                        color="primary"
                        size="small"
                    >
                        SELL
                    </Button>                    
                </Grid>
                <Grid>
                    <Card variant="outlined" >
                        <CardContent align="left">
                            <Typography align="left" color="#eabf13" variant="h6">
                                Today
                            </Typography>
                            <Box display="flex" flexDirection="row" bgcolor="background.paper">
                                <Card style={{ border: 'none', boxShadow: 'none' }}>
                                    <CardContent align="left">
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            High
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Low
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                    </CardContent>
                                </Card>
                                <Card style={{ border: 'none', boxShadow: 'none' }}>
                                    <CardContent align="left">
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Open
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Close
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Box>
                        </CardContent>
                    </Card>
                    <Card variant="outlined" >
                        <CardContent align="left">
                            <Typography align="left" color="#eabf13" variant="h6">
                                52 Week
                            </Typography>
                            <Box display="flex" flexDirection="row" bgcolor="background.paper">
                                <Card style={{ border: 'none', boxShadow: 'none' }}>
                                    <CardContent align="left">
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            High
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Low
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Box>
                        </CardContent>
                    </Card>
                    <Card variant="outlined" >
                        <CardContent align="left">
                            <Typography align="left" color="#eabf13" variant="h6">
                                Volume
                            </Typography>
                            <Box display="flex" flexDirection="row" bgcolor="background.paper">
                                <Card style={{ border: 'none', boxShadow: 'none' }}>
                                    <CardContent align="left">
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Today
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                        <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                        <Typography align="left" color="#eabf13" variant="body2">
                                            Average
                                        </Typography>
                                        <Box height={20} width={20} alignItems="left" justifyContent="left">
                                            <Typography align="left" variant="body2" gutterBottom>
                                                {price}
                                            </Typography>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>
            </Box>
        </Container>
    );
}


