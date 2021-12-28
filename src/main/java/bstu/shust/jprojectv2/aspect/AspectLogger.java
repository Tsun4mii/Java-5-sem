package bstu.shust.jprojectv2.aspect;

import bstu.shust.jprojectv2.rest.MainRestController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AspectLogger {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AspectLogger.class);

    @Pointcut("execution(* bstu.shust.jprojectv2.rest.MainRestController.*(..))")
    public void calledAtMainREstController(){}

    @After("calledAtMainREstController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.shust.jprojectv2.rest.ScooterRestController.*(..))")
    public void calledAtScooterRestController(){}

    @After("calledAtScooterRestController()")
    public void log2(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.shust.jprojectv2.rest.UserRentRestController.*(..))")
    public void calledAtUserRentRestController(){}

    @After("calledAtUserRentRestController()")
    public void log3(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
