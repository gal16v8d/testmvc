package co.com.gsdd.test.rest.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.NoArgsConstructor;

/**
 * Web Configuration expose the all services
 */
@NoArgsConstructor
@Configuration
@ComponentScan("co.com.gsdd.test.rest.api.services")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

}
