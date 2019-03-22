/**
 * 
 */
package co.com.gsdd.test.bo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBO {

	private static final String SPRING_CUSTOMER_XML = "Spring-Customer.xml";
	private static ClassPathXmlApplicationContext springContext;

	public static void initContext() {
		springContext = new ClassPathXmlApplicationContext(SPRING_CUSTOMER_XML);
	}

	public static <T> T getBean(Class<T> clazz, String beanName) {
		// open/read the application context file
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(
					SPRING_CUSTOMER_XML);
		}
		return springContext.getBean(beanName, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDataSource(String dataSourceName) {
		// open/read the application context file
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(
					SPRING_CUSTOMER_XML);
		}
		return (T) springContext.getBean(dataSourceName);
	}

}
