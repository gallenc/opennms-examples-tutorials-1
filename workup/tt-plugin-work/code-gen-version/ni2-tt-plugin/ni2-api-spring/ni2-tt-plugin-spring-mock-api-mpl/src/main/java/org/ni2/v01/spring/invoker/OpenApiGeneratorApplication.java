package org.ni2.v01.spring.invoker;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"org.ni2.v01.spring.invoker", "org.ni2.v01.spring.api" , "org.ni2.v01.spring.config"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)

// note modified to run as war - see https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.traditional-deployment
public class OpenApiGeneratorApplication extends SpringBootServletInitializer {
   
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources( OpenApiGeneratorApplication.class);
   }

    public static void main(String[] args) {
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
    }

    @Bean(name = "org.ni2.v01.spring.invoker.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}