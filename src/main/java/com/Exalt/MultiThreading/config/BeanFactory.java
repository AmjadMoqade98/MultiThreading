package com.Exalt.MultiThreading.config;

import com.devskiller.friendly_id.FriendlyId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean
    public ModelMapper ModelMapperObject() {
        return new ModelMapper();
    }

    @Bean
    public FriendlyId FriendlyIdObject() {
        return new FriendlyId();
    }

}
