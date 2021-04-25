package example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postID;

    @Column(name = "text")
    private String text;

    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "media_url")
    private String mediaURL;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_FK", nullable = false)
    private UserAccount author;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Reaction> reactions;

    public Post() {
    }

    public Post(int postID, String text, Timestamp dateCreated, String mediaURL, UserAccount author, List<Reaction> reactions) {
        this.postID = postID;
        this.text = text;
        this.dateCreated = dateCreated;
        this.mediaURL = mediaURL;
        this.author = author;
        this.reactions = reactions;
    }

    public Post(String text, Timestamp dateCreated, String mediaURL, UserAccount author) {
        this.text = text;
        this.dateCreated = dateCreated;
        this.mediaURL = mediaURL;
        this.author = author;
        this.reactions = new ArrayList<>();
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "\nPost{" +
                "postID=" + postID +
                ", text='" + text + '\'' +
                ", dateCreated=" + dateCreated +
                ", mediaURL='" + mediaURL + '\'' +
                //", author=" + author.getUsername() +
                '}';
    }
}
