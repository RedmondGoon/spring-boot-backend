# Spring Project 

# API Documentation :
##  POST /trade/trade:
___
This API is for client to place a trade. the api will return a string. 
The request body should be in JSON as the example below
<code>
{
    
        "userid": 1, 
        "ticker": "IBM",
        "price": 1010000.2,
        "volume":130,
        "type": "BUY"
   

}
</code>

The API will either "Sell successful" , "Buy successful", or "Transaction Unsuccessful"


## POST /user/newuser
____
This API is used to create a new users, The API will return "save" if succesful. The request
should be in JSON in the following format.

<code>
{
	"name": "Jayi",
	"email": "Nic@someemail.com",
	"balance":100000
}
</code>