package org.ni2.v01.spring.config;

import org.ni2.v01.spring.model.AddressCreateBodyCustomAttributesValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig {
   

    @Bean
    public SimpleModule simpleModule() {
        SimpleModule simpleModule = new SimpleModule()
                 .addAbstractTypeMapping(AddressCreateBodyCustomAttributesValue.class, TroubleTicketCustomAttributesValueImpl.class);
        
        return simpleModule;
    }
}