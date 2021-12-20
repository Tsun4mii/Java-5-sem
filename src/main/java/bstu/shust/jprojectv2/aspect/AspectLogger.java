package bstu.shust.jprojectv2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AspectLogger {
    private Logger log = Logger.getLogger(getClass().toString());

    @After("execution(* bstu.shust.jprojectv2.rest.MainRestController.*(..))")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
