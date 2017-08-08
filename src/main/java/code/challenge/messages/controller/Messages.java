package code.challenge.messages.controller;

import code.challenge.messages.dto.MessageDto;
import code.challenge.messages.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class Messages {

    @Autowired
    private MessagesService messagesService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void postMessage(@RequestBody MessageDto message){
        messagesService.post(message.getUser(), message.getMessage());
    }

    @RequestMapping(value = "/wall/{userName}", method = RequestMethod.GET)
    public List<MessageDto> wall(@PathVariable("userName") String userName){
        return messagesService.wall(userName);
    }

    @RequestMapping(value = "/timeline/{userName}", method = RequestMethod.GET)
    public List<MessageDto> timeline(@PathVariable("userName") String userName){
        return messagesService.timeline(userName);
    }

}
