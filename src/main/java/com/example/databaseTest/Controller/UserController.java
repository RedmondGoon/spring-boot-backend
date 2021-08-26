package com.example.databaseTest.Controller;


import com.example.databaseTest.DatabaseHandler.UserRepository;
import com.example.databaseTest.DatabaseIdentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/newuser") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestBody Map<String,String> req) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        String name= req.get("name");
        String email= req.get("email");
        double balance = Double.parseDouble(req.get("balance"));
        User newuser = new User();
        newuser.setBalance(balance);
        newuser.setName(name);
        newuser.setEmail(email);

        userRepository.save(newuser);

        return "Saved";
    }
}
