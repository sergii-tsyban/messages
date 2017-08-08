package code.challenge.messages.controller;

import code.challenge.messages.dto.UsersDto;
import code.challenge.messages.model.User;
import code.challenge.messages.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class Users {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userName}/follow", method = RequestMethod.POST)
    public void follow(@PathVariable("userName") String userName, @RequestBody UsersDto users) {
        userService.follow(userName, users.getUsers());
    }

    @RequestMapping(value = "/{userName}/unfollow", method = RequestMethod.POST)
    public void stopFollow(@PathVariable("userName") String userName, @RequestBody UsersDto users) {
        userService.stopFollow(userName, users.getUsers());
    }

    @RequestMapping(value = "/{userName}/followers", method = RequestMethod.GET)
    public UsersDto followers(@PathVariable("userName") String userName) {
        return userService.followers(userName);
    }

}
