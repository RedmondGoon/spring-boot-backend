package com.example.databaseTest.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.databaseTest.DatabaseHandler.TradeServiceHandler;
import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/trade")
public class TradeTransactionController {

    @Autowired
    private TradeServiceHandler tradeServiceHandler;

    @PostMapping(path="/trade") // Map ONLY POST Requests
    public @ResponseBody String trade (@RequestBody Map<String,String> req) {
        String type = req.get("type");
        String ticker= req.get("ticker");
        double price = Double.parseDouble(req.get("price"));
        int volume = Integer.parseInt(req.get("quantity"));
        int userid = Integer.parseInt(req.get("userid"));

        String r_msg =tradeServiceHandler.MakeTransaction( userid, volume, ticker,  price, type);
        return r_msg;

    }
}
