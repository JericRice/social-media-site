package example.controllers;

import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;
import example.services.PostService;
import example.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/reactions")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ReactionController {

    private ReactionService reactionService;
    private PostService postService;


    @PostMapping(value = "/createReaction")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Reaction createReaction(@RequestParam int value, @RequestBody Post post, HttpSession session)
    {
        System.out.println("In createReaction");

        UserAccount currentUser = (UserAccount) session.getAttribute("currentUser");
        System.out.println(currentUser);
        if (checkNewReaction(post, currentUser))
        {
            System.out.println("new reaction!");
            Reaction newReaction = new Reaction(value, currentUser, post);
            reactionService.addReaction(newReaction);
            return newReaction;
        }
        else{
            System.out.println("duplicate reaction found");
            return null;
        }
    }

    @PutMapping(value = "/updateReaction")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Reaction updateReaction(@RequestBody Post post, HttpSession session)
    {
        System.out.println("In updateReaction");

        UserAccount currentUser = (UserAccount) session.getAttribute("currentUser");
        Reaction existingReaction = reactionService.getUserReactionOnPost(currentUser, post);

        if (existingReaction != null)
        {
            System.out.println("Reaction updated!");
            return reactionService.toggleReaction(existingReaction);
        }
        else
        {
            return null;
        }
    }



    @Autowired
    public ReactionController(ReactionService reactionService, PostService postService) {
        this.reactionService = reactionService;
        this.postService = postService;
    }

    public ReactionController() {
    }

    public ReactionService getReactionService() {
        return reactionService;
    }

    public void setReactionService(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public boolean checkNewReaction(Post post, UserAccount user){
        Reaction existingReaction = reactionService.getUserReactionOnPost(user, post);
        System.out.println("Checking new reaction...");
        System.out.println(existingReaction);
        return existingReaction == null;
    }
}
