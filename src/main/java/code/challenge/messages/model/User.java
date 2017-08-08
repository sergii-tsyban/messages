package code.challenge.messages.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "follow_by")},
            inverseJoinColumns = {@JoinColumn(name = "follow_to")})
    private List<User> followedBy = new ArrayList<>();

    @ManyToMany(mappedBy = "followedBy")
    private List<User> followTo = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowedBy() {
        return followedBy;
    }

    public List<User> getFollowTo() {
        return followTo;
    }

    public void addUsersToFollow(List<User> users) {
        this.followTo.addAll(users);
    }

    public void removeUsersToFollow(List<User> users) {
        this.followTo.removeAll(users);
    }

    public void addFollower(User user){
        followedBy.add(user);
    }
}
