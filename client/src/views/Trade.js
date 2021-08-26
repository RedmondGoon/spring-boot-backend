import React, {useEffect} from 'react';
import { Container } from '@material-ui/core';
import TradesTable from '../ui/TradesTable';

export default function Trade() {

    function createData(name, qty, status, price, amt) {
        return { name, qty, status, price, amt };
    }
      
    const rows = [
        createData('AAPL', 159, "FILLED", 25, 4.0),
        createData('IBM', 237, "FILLED", 37, 4.3),
        createData('SE', 262, "FILLED", 24, 6.0),
    ];

    return (
        <Container>
            <TradesTable rows = {rows}/>
        </Container>
    );
}
