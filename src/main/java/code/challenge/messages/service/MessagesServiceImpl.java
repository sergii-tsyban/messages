package code.challenge.messages.service;

import code.challenge.messages.dto.MessageDto;
import code.challenge.messages.model.Message;
import code.challenge.messages.model.User;
import code.challenge.messages.persistence.MessageRepository;
import code.challenge.messages.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesServiceImpl implements MessagesService{

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    public MessagesServiceImpl(UserService userService, MessageRepository messageRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public void post(String userName, String content) {
        User user = userService.findByName(userName).orElse(userService.createUser(userName));
        Message message = new Message();
        message.setPostedDate(new Date());
        message.setContent(content);
        message.setPostedBy(user);
        messageRepository.save(message);
    }

    @Override
    @Transactional
    public List<MessageDto> wall(String userName) {
        return messageRepository.findByUserDesc(userName).map(BeanMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> timeline(String userName) {
        return messageRepository.findByFollowerDesc(userName).map(BeanMapper::modelToDto).collect(Collectors.toList());
    }
}
