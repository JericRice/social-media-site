package example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import example.utils.UUIDGenerator;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserAccount")
public class UserAccount {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length=15)
    private String username;

    @Column(name = "password", nullable = false, length=150)
    private String password;

    @Column(name = "first_name", nullable = false, length=20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length=20)
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarURL;

    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "bio", length=500)
    private String bio;

    @Column(name = "gender", length=20)
    private String gender;

    @Column(name = "relationship_status", length=25)
    private String relationshipStatus;

    @Column(name = "orientation", length=15)
    private String orientation;

    @Column(name = "secret_token")
    private int secretToken;

    @JsonBackReference
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "reactor", fetch = FetchType.LAZY)
    private List<Reaction> reactions;

    public UserAccount() {
    }

    /**
     * This is a filled in constructor for getting values from the db
     *
     * @param userID
     * @param email
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param avatarURL
     * @param dateCreated
     * @param birthday
     * @param bio
     * @param gender
     * @param relationshipStatus
     * @param orientation
     * @param secretToken
     * @param posts
     * @param reactions
     */
    public UserAccount(int userID, String email, String username, String password, String firstName, String lastName,
                       String avatarURL, Timestamp dateCreated, Timestamp birthday, String bio, String gender,
                       String relationshipStatus, String orientation, int secretToken, List<Post> posts,
                       List<Reaction> reactions) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarURL = avatarURL;
        this.dateCreated = dateCreated;
        this.birthday = birthday;
        this.bio = bio;
        this.gender = gender;
        this.relationshipStatus = relationshipStatus;
        this.orientation = orientation;
        this.secretToken = secretToken;
        this.posts = posts;
        this.reactions = reactions;
    }

    /**
     * This is for a brand new UserAccount.
     *
     * Generates empty values for bio, avatarURL, orientation, relationshipStatus, posts, and reactions
     * userID, DateCreated, and secretToken generated
     *
     * @param email
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param birthday
     * @param gender
     */
    public UserAccount(String email, String username, String password, String firstName, String lastName,
                       Timestamp birthday, String gender) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;

        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.bio = "";
        this.avatarURL = "";
        this.orientation = "";
        this.relationshipStatus = "";
        UUIDGenerator generator = new UUIDGenerator();
        this.secretToken = generator.generateUniqueId();
//        System.out.println(secretToken);
        this.posts = new ArrayList<>();
        this.reactions = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(int secretToken) {
        this.secretToken = secretToken;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", dateCreated=" + dateCreated +
                ", birthday=" + birthday +
                ", bio='" + bio + '\'' +
                ", gender='" + gender + '\'' +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", orientation='" + orientation + '\'' +
                ", secretToken=" + secretToken +
                '}';
    }
}
