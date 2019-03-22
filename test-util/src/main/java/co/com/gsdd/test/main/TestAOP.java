package co.com.gsdd.test.main;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class only for practice AOP.
 * 
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
@Aspect
public class TestAOP {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestAOP.class);
	public static final String METHOD = "execution(* co.com.gsdd.test.bo..*(..))";

	/**
	 * {@inheritDoc}
	 */
	@Around(METHOD)
	public Object invoke(ProceedingJoinPoint jp) throws Throwable {
		// Original method args
		LOGGER.info(Arrays.toString(jp.getArgs()));
		// use MethodBeforeAdvice
		try {
			// Proceed to call original method
			Object result = jp.proceed();
			// use AfterReturningAdvice
			return result;
		} catch (IllegalArgumentException e) {
			// use ThrowsAdvice
			throw e;
		}
	}

}
