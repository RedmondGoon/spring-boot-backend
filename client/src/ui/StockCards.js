import "../views/Stocks.css";
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
    Paper,
    Divider
} from '@material-ui/core';
import PropTypes from "prop-types";

const useStyles = makeStyles({
    root: {
      minWidth: 130,
    },
    title: {
      fontSize: 14,
    },
  });

export default function StockCards(props) {
    const classes = useStyles();
    const {title, key1, value1, key2, value2, key3, value3, key4, value4} = props;

    return (
        <div className="stock-holder">
                <Card className={classes.root}>
                    <CardContent>
                        <Typography variant="h6">
                            {title}
                        </Typography>
                        <Box display="flex" flexDirection="row">         
                            <Box>
                                <Typography>
                                    {key1}
                                </Typography>
                                <Typography>
                                    {value1}
                                </Typography>
                                <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                <Typography>
                                    {key2}
                                </Typography>
                                <Typography>
                                    {value2}
                                </Typography>
                            </Box>
                            <Box height={20} width={40} alignItems="center" justifyContent="center" />
                            <Box>
                                <Typography>
                                    {key3}
                                </Typography>
                                <Typography>
                                    {value3}
                                </Typography>
                                <Box height={20} width={40} alignItems="center" justifyContent="center" />
                                <Typography>
                                    {key4}
                                </Typography>
                                <Typography>
                                    {value4}
                                </Typography>
                            </Box>
                        </Box>
                    </CardContent>
                </Card>
        </div>
    );

}

StockCards.propTypes = {
    title: PropTypes.any,
    key1: PropTypes.any,
    value1: PropTypes.any,
    key2: PropTypes.any,
    value2: PropTypes.any,
    key3: PropTypes.any,
    value3: PropTypes.any,
    key4: PropTypes.any,
    value4: PropTypes.any,
}