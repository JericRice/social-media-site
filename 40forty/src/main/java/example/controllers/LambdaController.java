package example.controllers;

import example.models.UserAccount;
import example.services.LambdaService;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lambda")
@CrossOrigin(originPatterns = "")
public class LambdaController {
    LambdaService lambdaService;

    public LambdaController(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }

    public LambdaController() {
    }

    @DeleteMapping(value = "/deleteAllData")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String deleteAllData(){
        System.out.println("in deleteAllData()");

        try {
            lambdaService.deleteAllData();
            return "Good";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Did not work :/");
            return "Bad";
        }
    }

    public LambdaService getLambdaService() {
        return lambdaService;
    }

    @Autowired
    public void setLambdaService(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }
}
