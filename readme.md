# Spring Project 

# API Documentation :
##  POST /transaction:
___
This API is for client to place a trade. the api will return a string. 
The request body should be in JSON as the example below
<code>
{
    
        "accId": 1, 
        "ticker": "IBM",
        "quantity": 4,
        "execPrice": 1000.0,
        "actionType": "BUY"
   

}
</code>

Successful requests will have a response string of "Sell order successfully placed" , or "Buy order successfully placed".


## POST /account
____
This API is used to create a new account, The API will return "save" account if successful. The request
should be in JSON in the following format.
{"password":"123", "firstName":"Redmond", "lastName":"Goon", "email":"redmond_g@hotmail.com"}
<code>
{
	"password": "123",
	"firstName": "Redmond",
    "lastName": "Goon",
	"email": "redmond_g@hotmail.com"
}
</code>