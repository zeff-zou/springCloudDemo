package com.clouddemo.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by zzf on 2017/11/16.
 */
public interface ConsumerService {
    OAuth2AccessToken login(String username, String password);
}
