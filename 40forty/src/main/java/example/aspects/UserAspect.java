package example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component(value = "userAspect")
@Aspect
public class UserAspect {

    //@Before("!execution(* example.controllers.LoginController.test (..))")
    public void validateUser(JoinPoint joinPoint) {

        System.out.println("in user aspect validate user");
        System.out.println(joinPoint.getArgs());
//        HttpSession session = joinPoint.getArgs();
    }
}
