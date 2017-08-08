package code.challenge.messages.service;

import code.challenge.messages.dto.MessageDto;

import java.util.List;

public interface MessagesService {

    void post(String userName, String message);

    List<MessageDto> wall(String userName);

    List<MessageDto> timeline(String userName);
}
