package poc.security.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 310280812 on 3/27/2017.
 */
@Slf4j
@Aspect
@Component
public class AuditLoggingAspect {

    @Autowired
    private AnnotationExpressionEvaluator annotationExpressionEvaluator;

    @Around("within(poc.security..*) && @annotation(auditLogging)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, AuditLogging auditLogging) throws Throwable {
        try {
           log.info("Auditing: {}", proceedingJoinPoint.getSignature());
           Object annotationValue = retrieveAnnotationValue(proceedingJoinPoint, auditLogging);
           Object[] args = proceedingJoinPoint.getArgs();
           Object result = proceedingJoinPoint.proceed(args);
           log.info("Audting - Logging the result {}", result);
           return result;
        } catch (Throwable th) {
            log.error("Auditing - Error: {} - Stacktrace {}", th.getMessage(), th.getStackTrace());
            throw th;
        }
    }

    private Object retrieveAnnotationValue(ProceedingJoinPoint proceedingJoinPoint, AuditLogging auditLogging) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        return annotationExpressionEvaluator.evaluate(auditLogging.expression(), proceedingJoinPoint.getArgs(), signature.getParameterNames());
    }
}
