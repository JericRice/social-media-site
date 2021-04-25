package example.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

//@Entity
//@Table(name="password_token")
public class PasswordToken {
//    @Id
//    @Column(name="token_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int tokenID;
//
//    @Column(name = "token")
//    private String token;
//
//    @Column(name = "expiration_date")
//    private Timestamp expirationDate;
//
//    @OneToOne(mappedBy = "passwordToken", fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_FK")
//    private UserAccount tokenUser;
//
//    public PasswordToken() {
//    }
//
//    public PasswordToken(int tokenID, String token, Timestamp expirationDate, UserAccount tokenUser) {
//        this.tokenID = tokenID;
//        this.token = token;
//        this.expirationDate = expirationDate;
//        this.tokenUser = tokenUser;
//    }
//
//    public int getTokenID() {
//        return tokenID;
//    }
//
//    public void setTokenID(int tokenID) {
//        this.tokenID = tokenID;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public Date getExpiryDate() {
//        return expirationDate;
//    }
//
//    public void setExpiryDate(Timestamp expirationDate) {
//        this.expirationDate = expirationDate;
//    }
//
//    public void setExpiryDate(int minutes){
//        Calendar now = Calendar.getInstance();
//        now.add(Calendar.MINUTE, minutes);
//        this.expirationDate = new Timestamp(now.getTimeInMillis());
//    }
//
//    public boolean isExpired() {
//        return new Date().after(this.expirationDate);
//    }
//
//    public UserAccount getTokenUser() {
//        return tokenUser;
//    }
//
//    public void setTokenUser(UserAccount user) {
//        this.tokenUser = user;
//    }

}
