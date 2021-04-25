package example.controllers;

import example.utils.PasswordAuthentication;
import example.models.UserAccount;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

//TODO: use password encryption after basic functionality is done
@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
//@SessionAttributes("currentUser")
public class LoginController {

    UserService userService;

    /**
     * Checks incoming user and if it exists, we set the session attribute to that user.
     * @param session current session
     * @param currentUser user object sent through the http request
     * @return Either a String if the login was unsuccessful, or the User object that was logged in
     */
    @PostMapping(value = "/login")
    public Object login(HttpSession session, @RequestBody UserAccount currentUser) {

        System.out.println(session.getServletContext());
        System.out.println("login detected");
        PasswordAuthentication authorizer = new PasswordAuthentication();
        UserAccount loginUser ;

        try {

            loginUser = userService.getUserByUsername(currentUser.getUsername());
        }
        catch (NoResultException exception) {

            return "user does not exist";
        }

        boolean onHashPassword = authorizer.authenticate(currentUser.getPassword().toCharArray(), loginUser.getPassword());

        if (onHashPassword) {

            session.setAttribute("currentUser", loginUser);
            return loginUser;
        }

        else {

            return "username or password is incorrect";
        }
    }

    /**
     * Nulls the user attribute of the session
     * @param session current session
     * @return String that denotes the status of the logout
     */
    @PostMapping(value = "/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        // Added because of a bug when a user logs out then back in as someone else, maybe this'll fix it...
        // ...nope :/

        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            System.out.println(c);
            System.out.println(c.getValue());
            c.setMaxAge(0);
            c.setValue(null);
            c.setPath("/");

            response.addCookie(c);
        }
        System.out.println(session.getAttribute("currentUser"));
        session.setAttribute("currentUser", null);
        System.out.println(session.getAttribute("currentUser"));
        session.invalidate();

        System.out.println("In logout!");
        System.out.println("logout successful");
    }

    public LoginController() {
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
