package com.clouddemo.service.impl;

import com.clouddemo.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

/**
 * Created by zzf on 2017/11/16.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private Resource resource;
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public Resource ResourceOwnerPasswordResourceDetails() {
        return new Resource();
    }

    class Resource extends ResourceOwnerPasswordResourceDetails {

        @Override
        public boolean isClientOnly() {
            return true;
        }
    }
    @Override
    public OAuth2AccessToken login(String username, String password) {
        resource.setUsername(username);
        resource.setPassword(password);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource);
        return oAuth2RestTemplate.getAccessToken();
    }
}
