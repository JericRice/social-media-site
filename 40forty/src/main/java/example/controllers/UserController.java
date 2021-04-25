package example.controllers;

import example.models.UserAccount;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@SessionAttributes("currentUser")
public class UserController {

    private UserService userService;

    /**
     * Server endpoint handler
     * gets all users from the Service.
     *
     * @return List of all Users
     */
    @GetMapping(value = "/getAllUsers")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<UserAccount> getAllUsers(){
        System.out.println("in getAllUsers");

        return userService.getAllUsers();
    }


    /**
     * Server endpoint handler
     * gets a user by it's id
     * @param id id to search for
     * @return user object that represents the user with that id
     */
    @GetMapping(value = "/getUserById")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserAccount getUserById(@RequestParam("id")int id){
        return userService.getUserByID(id);
    }

    /**
     * Server endpoint handler
     * gets a user by it's username
     * @param username username to search for
     * @return user object that represents the user with that username
     */
    @GetMapping(value = "/getUserByUsername")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody UserAccount getUserByUsername(@RequestParam("username")String username){

        return userService.getUserByUsername(username);
    }

    /**
     * Server endpoint handler
     * gets a user by it's email
     * @param email email to search for
     * @return user object that represents the user with that email
     */
    @GetMapping(value = "/getUserByEmail")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody UserAccount getUserByEmail(@RequestParam("email")String email){
        return userService.getUserByEmail(email);
    }

    //probably going into the registration controllers
    /**
     * creates a new user in the database
     * @param incomingUser user object to add to the db
     */
    @PostMapping(value = "/createUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody UserAccount incomingUser){
        userService.addUser(incomingUser);
    }

    /**
     * updates a user with the information present in the param
     * @param updatedUser user object that contains the updated information about the user
     */
    @PostMapping(value = "/updateUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(UserAccount updatedUser, HttpSession session){
        //sessionattribute is null for some reason. current theory: it isnt finding the one that the client is using, so it auto-creates it's own
        UserAccount currentUser = (UserAccount) session.getAttribute("currentUser");
        System.out.println(session);
        System.out.println(currentUser);
        System.out.println(updatedUser);
        updatedUser = fillInUser(currentUser, updatedUser);
        System.out.println(updatedUser);
        userService.updateUser(updatedUser);

        session.setAttribute("currentUser",updatedUser);
    }

    //only for admin and maybe extra functionality later
    @DeleteMapping(value = "/deleteUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestBody UserAccount toBeDeleted){
        userService.deleteUser(toBeDeleted.getUserID());
    }


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
    }

    public UserService getUserService() {
//        userService.insertInitialValues();
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
//        userService.insertInitialValues();
    }

    public UserAccount fillInUser(UserAccount currentUser, UserAccount updatedUser){
        //prepare for a long if statement because i couldnt find a better way to do this
        if(updatedUser.getUserID() == 0){
            updatedUser.setUserID(currentUser.getUserID());
        }
        if(updatedUser.getAvatarURL() == null){
            updatedUser.setAvatarURL(currentUser.getAvatarURL());
        }
        if(updatedUser.getUsername() == null){
            updatedUser.setUsername(currentUser.getUsername());
        }
        if(updatedUser.getBio() == null){
            updatedUser.setBio(currentUser.getBio());
        }
        if(updatedUser.getEmail() == null){
            updatedUser.setEmail(currentUser.getEmail());
        }
        if(updatedUser.getFirstName() == null){
            updatedUser.setFirstName(currentUser.getFirstName());
        }
        if(updatedUser.getLastName() == null){
            updatedUser.setLastName(currentUser.getLastName());
        }
        if(updatedUser.getGender() == null){
            updatedUser.setGender(currentUser.getGender());
        }
        if(updatedUser.getOrientation() == null){
            updatedUser.setUsername(currentUser.getOrientation());
        }
        if(updatedUser.getRelationshipStatus() == null){
            updatedUser.setRelationshipStatus(currentUser.getRelationshipStatus());
        }
        if(updatedUser.getSecretToken() == 0){
            updatedUser.setUsername(currentUser.getUsername());
        }
        if(updatedUser.getDateCreated() == null){
            updatedUser.setDateCreated(currentUser.getDateCreated());
        }
        if(updatedUser.getBirthday() == null){
            updatedUser.setBirthday(currentUser.getBirthday());
        }

        System.out.println(currentUser);
        updatedUser.setPassword(currentUser.getPassword());


        return updatedUser;
    }
}
