import React, {useEffect, useState} from 'react';
import { Container, Typography } from '@material-ui/core';
import TradesTable from '../ui/TradesTable';
import { getTrades } from '../service/trades';

export default function Trade() {

    const [rows, setRows] = useState()
 
    useEffect(async () => {
        setRows(await getTrades());
    }, []);

    return (
        <Container>
            {rows == undefined ? (
                <Typography>
                    {""}
                </Typography>                                                                       
            ) : (
                <TradesTable rows = {rows}/> 
            )}
        </Container>
    );
}
