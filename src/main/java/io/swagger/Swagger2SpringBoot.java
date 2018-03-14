package io.swagger;

import io.swagger.api.ApiOriginFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan( basePackages = {"io.swagger", "io.swagger.api"} )
@EnableAutoConfiguration
public class Swagger2SpringBoot implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    @Override
    public void run(String... arg0) {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApiOriginFilter());
        registration.addUrlPatterns("*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
