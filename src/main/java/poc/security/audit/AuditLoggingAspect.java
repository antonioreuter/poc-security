package poc.security.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by 310280812 on 3/27/2017.
 */
@Slf4j
@Aspect
@Component
public class AuditLoggingAspect {

    @Around("within(poc.security..*) && @annotation(poc.security.audit.AuditLogging)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
           log.info("Auditing: {}", proceedingJoinPoint.getSignature());
           Object[] args = proceedingJoinPoint.getArgs();
           Object result = proceedingJoinPoint.proceed(args);
           log.info("Audting - Logging the result {}", result);
           return result;
        } catch (Throwable th) {
            log.error("Auditing - Error: {} - Stacktrace {}", th.getMessage(), th.getStackTrace());
            throw th;
        }
    }
}
