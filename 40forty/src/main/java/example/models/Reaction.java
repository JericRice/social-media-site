package example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Reaction")
public class Reaction {

    @Id
    @Column(name = "reaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reactionID;

    //-1 for bad, 1 for good
    @Column(name = "value")
    private int value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reactor_FK")
    private UserAccount reactor;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_FK")
    private Post post;

    public Reaction() {
    }

    public Reaction(int reactionID, int value, UserAccount reactor, Post post) {
        this.reactionID = reactionID;
        this.value = value;
        this.reactor = reactor;
        this.post = post;
    }

    public Reaction(int value, UserAccount reactor, Post post) {
        this.value = value;
        this.reactor = reactor;
        this.post = post;
    }

    public int getReactionID() {
        return reactionID;
    }

    public void setReactionID(int reactionID) {
        this.reactionID = reactionID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public UserAccount getReactor() {
        return reactor;
    }

    public void setReactor(UserAccount reactor) {
        this.reactor = reactor;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "reactionID=" + reactionID +
                ", value=" + value +
                ", reactor=" + reactor.getUsername() +
                ", post=" + post.getPostID() +
                '}';
    }
}

