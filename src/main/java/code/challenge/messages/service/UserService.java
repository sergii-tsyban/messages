package code.challenge.messages.service;

import code.challenge.messages.dto.UsersDto;
import code.challenge.messages.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByName(String userName);
    User createUser(String userName);
    void follow(String userName, List<String> followTo);
    void stopFollow(String userName, List<String> unfollowTo);
    UsersDto followers(String userName);
}
