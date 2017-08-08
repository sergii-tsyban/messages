package code.challenge.messages.service;

import code.challenge.messages.dto.UsersDto;
import code.challenge.messages.persistence.UserRepository;
import code.challenge.messages.exception.UserNotFoundException;
import code.challenge.messages.model.User;
import code.challenge.messages.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Optional<User> findByName(String userName) {
        return Optional.ofNullable(userRepository.findOne(userName));
    }

    @Override
    @Transactional
    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void follow(String userName, List<String> followTo) {
        User user = findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot add followers to non existing user: " + userName));
        userRepository.findAll(followTo).forEach(u -> u.addFollower(user));
    }

    @Override
    @Transactional
    public void stopFollow(String userName, List<String> stopFollowTo) {
        User user = findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot remove followers from non existing user: " + userName));
        List<User> users = userRepository.findAll(stopFollowTo);
        user.removeUsersToFollow(users);
    }

    @Override
    public UsersDto followers(String userName) {
        User user = findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot retrieve followers for non existing user: " + userName));
        return BeanMapper.modelToDto(user.getFollowedBy());
    }
}
