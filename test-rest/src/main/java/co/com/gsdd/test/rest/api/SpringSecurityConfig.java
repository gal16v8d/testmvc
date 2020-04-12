package co.com.gsdd.test.rest.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import lombok.NoArgsConstructor;


/**
 * Expose the Spring Security Configuration
 */
@NoArgsConstructor
@Configuration
@ImportResource({ "classpath:security-config.xml" })
@ComponentScan("co.com.gsdd.test.rest.api.security")
public class SpringSecurityConfig {

}
