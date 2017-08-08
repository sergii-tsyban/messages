package code.challenge.messages.persistence;

import code.challenge.messages.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.postedBy.name = :username order by posted_date desc")
    Stream<Message> findByUserDesc(@Param("username") String username);

    @Query("select m from Message m join m.postedBy pb join pb.followedBy fb where fb.name = :follower order by posted_date desc")
    Stream<Message> findByFollowerDesc(@Param("follower") String follower);

}
