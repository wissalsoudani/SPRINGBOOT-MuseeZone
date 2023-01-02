package tn.esprit.exam.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {


	@Around("execution(* tn.esprit.exam.generic.IGenericServiceImp.*.*(..))")
	public Object executionTime(ProceedingJoinPoint pjp) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
//	    List<Object> obj = new ArrayList<Object>(); // si le type de retour est une liste
//		obj = (List<Object>) pjp.proceed();
		Object obj = pjp.proceed();

		stopWatch.stop();

		String methodName = pjp.getSignature().getName();

		log.info("The runtime of the method ( "+ methodName + " ) = " + stopWatch.getTotalTimeMillis() + " milliseconds.");

		return obj;
	}
//	


}