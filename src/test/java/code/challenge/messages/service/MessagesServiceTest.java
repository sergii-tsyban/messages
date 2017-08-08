package code.challenge.messages.service;

import code.challenge.messages.dto.MessageDto;
import code.challenge.messages.model.Message;
import code.challenge.messages.model.User;
import code.challenge.messages.persistence.MessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MessagesServiceTest {

    private MessagesService messagesService;

    private UserService userServiceMock;
    private MessageRepository messageRepositoryMock;

    @Before
    public void setUp(){
        userServiceMock = Mockito.mock(UserService.class);
        messageRepositoryMock = Mockito.mock(MessageRepository.class);
        messagesService = new MessagesServiceImpl(userServiceMock, messageRepositoryMock);
    }

    @Test
    public void testPost(){
        User user = new User();
        user.setName("john");

        Mockito.when(userServiceMock.findByName(user.getName())).thenReturn(Optional.of(user));

        messagesService.post(user.getName(), "hello");

        Mockito.verify(userServiceMock, Mockito.times(1)).findByName(user.getName());
        Mockito.verify(messageRepositoryMock, Mockito.times(1)).save(Mockito.any(Message.class));
    }

    @Test
    public void testWall(){
        Message message = new Message();
        message.setContent("hello");
        message.setPostedDate(new Date());
        User user = new User();
        user.setName("john");
        message.setPostedBy(user);

        Stream<Message> mockedMessages = Stream.of(message);

        Mockito.when(messageRepositoryMock.findByUserDesc(user.getName())).thenReturn(mockedMessages);

        List<MessageDto> dtos = messagesService.wall(user.getName());

        Mockito.verify(messageRepositoryMock, Mockito.times(1)).findByUserDesc(user.getName());
        dtos.forEach(dto -> {
            Assert.assertNotNull(dto.getPostedDate());
            Assert.assertEquals(dto.getUser(), message.getPostedBy().getName());
            Assert.assertEquals(dto.getMessage(), message.getContent());
        });
    }

    @Test
    public void testTimeline(){
        Message message = new Message();
        message.setContent("hello");
        message.setPostedDate(new Date());
        User user = new User();
        user.setName("john");
        message.setPostedBy(user);

        User follower = new User();
        follower.setName("bill");
        user.getFollowedBy().add(follower);
        follower.getFollowTo().add(user);

        Stream<Message> mockedMessages = Stream.of(message);

        Mockito.when(messageRepositoryMock.findByFollowerDesc(follower.getName())).thenReturn(mockedMessages);

        List<MessageDto> dtos = messagesService.timeline(follower.getName());

        Mockito.verify(messageRepositoryMock, Mockito.times(1)).findByFollowerDesc(follower.getName());
        dtos.forEach(dto -> {
            Assert.assertNotNull(dto.getPostedDate());
            Assert.assertEquals(dto.getUser(), message.getPostedBy().getName());
            Assert.assertEquals(dto.getMessage(), message.getContent());
        });
    }

}
