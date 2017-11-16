package com.springbootdemo.config.security;

import com.springbootdemo.member.domain.User;
import com.springbootdemo.member.repository.AuthensRepository;
import com.springbootdemo.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zzf on 2017/11/15.
 */
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthensRepository authensRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null) {
            throw new RuntimeException("无效的账号或密码");
        }
        user.setAuthenss(authensRepository.findByUsername(username));
        return user;
    }
}
