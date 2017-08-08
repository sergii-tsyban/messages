package code.challenge.messages.utils;

import code.challenge.messages.dto.MessageDto;
import code.challenge.messages.dto.UsersDto;
import code.challenge.messages.model.Message;
import code.challenge.messages.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class BeanMapper {

    public static MessageDto modelToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(message.getContent());
        messageDto.setUser(message.getPostedBy().getName());
        messageDto.setPostedDate(message.getPostedDate());
        return messageDto;
    }

    public static UsersDto modelToDto(List<User> users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUsers(users.stream().map(User::getName).collect(Collectors.toList()));
        return usersDto;
    }

}
