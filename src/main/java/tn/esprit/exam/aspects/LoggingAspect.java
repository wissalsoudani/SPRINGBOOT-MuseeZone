package tn.esprit.exam.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	
	@After("execution(* tn.esprit.exam.Controllers.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		
		String name = joinPoint.getSignature().getName();
		
		log.info("méthode exécutée:" + name );
	}

}	