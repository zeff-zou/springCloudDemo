package com.springbootdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import javax.sql.DataSource;

/**
 * Created by zzf on 2017/11/16.
 */
@Configuration
@EnableAuthorizationServer//开启配置 OAuth 2.0 认证授权服务
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthAuthorizeConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {//配置客户端详情信息
        clients.jdbc(dataSource);//配置从数据库将客户端信息取出来
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
                // TokenEndPoint @ExceptionHandler会拦截掉一些异常信息，我们需要重新包装
                .exceptionTranslator(new CustomDefaultWebResponseExceptionTranslator())
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }
    private class CustomDefaultWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

        @Override
        public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
            /**
             * 直接抛出，由 ExceptionFilter 统一处理
             * 否则会被 TokenEndPoint @ExceptionHandler 处理
             */
            throw e;
        }

    }
}
