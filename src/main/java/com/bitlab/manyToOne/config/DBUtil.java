package com.bitlab.manyToOne.config;


import com.bitlab.manyToOne.models.User;
import com.bitlab.manyToOne.services.ApplicationRequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBUtil {

    @Bean
    public ApplicationRequestService applicationRequestService(){
        return new ApplicationRequestService();
    }

    @Bean
    public User user(){
        return new User();
    }
}