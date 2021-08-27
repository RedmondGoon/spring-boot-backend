package com.example.databaseTest.ExternalAPI;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.util.*;

public class AlphaVantage {
    private String apikey;
    public AlphaVantage(String apikey ) {
        this.apikey=apikey;
    }

    public static void main(String[] args) {
        System.out.println(AlphaVantage.getQuotes("SE"));
        List<String> symbols= new ArrayList<>();
        symbols.add("IBM");
        symbols.add("SPY");
        symbols.add("AAPL");
        System.out.println(AlphaVantage.getListOfStock(symbols));
        System.out.println(AlphaVantage.getStockQuoteAWS("SPY"));
    }
    public static Double getStockQuoteAWS(String symbol){
        String baseurl ="https://65yrtzxkv2.execute-api.ap-southeast-1.amazonaws.com/SinglePrice?symbol=%s";
        String apiurl = String.format(baseurl, symbol);
        try {
            JSONObject msg = JsonReader.readJsonFromUrl(apiurl);
            double price =Double.parseDouble( msg.get("price").toString());
            return price;
        }catch (Exception e){
            System.out.println("Json Error");
        }
        return  null;
    }
    public JSONObject getStockQuote (String symbol){

        String baseurl ="https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";
        String apiurl= String.format(baseurl, symbol, this.apikey);

        try {
            JSONObject msg = JsonReader.readJsonFromUrl(apiurl);
            JSONObject quote= msg.getJSONObject("Global Quote");
            return quote;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Map<String,Double> getListOfStock(List<String> symbols){
        HashMap<String,Double> priceList = new HashMap<>();

        for (int i =0;i <symbols.size(); i++ ){
            String symbol = symbols.get(i);
            double this_price= AlphaVantage.getStockQuoteAWS(symbol);
            priceList.put(symbol,this_price);

        }
        return priceList;
    }
    public static Double getQuotes(String symbol){
        String apikey= "DKPTD0M35AWT42WD";
        AlphaVantage api = new AlphaVantage(apikey);
        JSONObject stockQuote=  api.getStockQuote(symbol);
        String priceskey = AlphaVantage.getType("price");
        try {
            double price = Double.parseDouble(stockQuote.get(priceskey).toString());
            return price;
        }catch (Exception e){
            return null;
        }

    }
    public static String getType(String types){

        switch (types){
            case "open":
                return "02. open";
            case "close":
                return "08. previous close";
            case "price":
                return "05. price";
            default:
                return "05. price";
        }
    }
}
