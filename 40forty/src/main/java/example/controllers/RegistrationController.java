package example.controllers;

import example.utils.PasswordAuthentication;
import example.models.PasswordToken;
import example.models.UserAccount;
import example.services.UserService;
import example.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class RegistrationController
{
    private UserServiceImpl userService;

    /**
     * Registers a new User
     *
     * Checks if the given User's username already exists in the DB
     * Hashes the given User's password and stores the data in the DB
     *
     * @param session the session
     * @param newUser a User object
     * @return Strings to declare success or failure
     */
    @PostMapping(value = "/register")
    public Object register(HttpSession session, @RequestBody UserAccount newUser)
    {
        String newUsername = newUser.getUsername();

        // Check if the user is there and it was made into a proper UserAccount
        // Then, check if the username exists in the DB, only continue if it does not
        // Split these up because I might've coded them wrong lol
        if(newUser == null || !(newUser instanceof UserAccount))
        {
            return "BAD user : Registration Failed";
        }

        if(userService.usernameExists(newUsername))
        {
            return "BAD username exists : Registration Failed";
        }


        // authorizer authorizes passwords, and hashes them too
        PasswordAuthentication authorizer = new PasswordAuthentication();
        String hashedPassword = authorizer.hash(newUser.getPassword().toCharArray());
        PasswordToken passwordToken = new PasswordToken();

        // We have to make a new user so that the userID values stay in sync
        // Because of this, maybe using strings instead of full objects would be faster
        UserAccount secretUser = new UserAccount(newUser.getEmail(), newUser.getUsername(), hashedPassword,
                newUser.getFirstName(), newUser.getLastName(), newUser.getBirthday(), newUser.getGender());

        // Add the user to the DB and log them in
        // Redirection logic should be here too
        userService.addUser(secretUser);
        session.setAttribute("currentUser", secretUser);
        return secretUser;
    }

    public RegistrationController() {
    }

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
