package com.second.springbootdemo.Controller;

import com.second.springbootdemo.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class DemoController {
    @GetMapping
    public String Greeting(){
        return "Hello guys";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return"You're welcome to our class";
    }

    public ResponseEntity<String> login(LoginRequest request){
        String message;
       if(request.getUsername().equalsIgnoreCase("iacademy")&&
               request.getPassword().equalsIgnoreCase("1234"))
       {message = "You successfully login";
           return ResponseEntity.ok(message);

       }else {
           message = "Wrong request";
           return ResponseEntity.badRequest().body(message);
       }
    }
}
