package com.kodhnk.base.log;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogEntryRepository logEntryRepository;

    @Pointcut("execution(* com.kodhnk.base.services..*(..))")
    public void serviceMethods() {}

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        LogEntry logEntry = new LogEntry();
        logEntry.setLevel("INFO");
        logEntry.setMessage("Method executed successfully: " + joinPoint.getSignature().toShortString());
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setMethod(joinPoint.getSignature().toShortString());
        logEntryRepository.save(logEntry);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        LogEntry logEntry = new LogEntry();
        logEntry.setLevel("ERROR");
        logEntry.setMessage("Method threw an exception: " + joinPoint.getSignature().toShortString() + " Exception: " + exception.getMessage());
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setMethod(joinPoint.getSignature().toShortString());
        logEntryRepository.save(logEntry);
    }
}