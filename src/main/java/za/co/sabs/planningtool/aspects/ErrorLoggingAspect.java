//package za.co.sabs.planningtool.aspects;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class ErrorLoggingAspect {
//    @AfterThrowing(pointcut = "execution(* za.co.sabs.planningtool.*..*(..))", throwing = "error")
//    public void afterThrowingAdvice(JoinPoint jp, Throwable error) {
//        System.out.println(":::::::::::::::::::::::::::::::::::::\n");
//        System.out.println("::::::::::: Error Details :::::::::::\n");
//        System.out.println("::: Method Signature: " + jp.getSignature());
//        System.out.println("::: Exception Message: " + error.getMessage());
//        System.out.println("::::::::::: End of Error ::::::::::::\n");
//        System.out.println(":::::::::::::::::::::::::::::::::::::\n");
//    }
//}
