package code.challenge.messages.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alive")
public class IsAlive {

    @RequestMapping
    public String isAlive(){
        return "Messages service is up and running";
    }

}
