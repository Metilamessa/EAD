package com.example.bookstore;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* BookRegistrationServlet.*(..))")
    public void logBeforeMethods() {
        System.out.println("A method in BookRegistrationServlet is being executed.");
    }
}
