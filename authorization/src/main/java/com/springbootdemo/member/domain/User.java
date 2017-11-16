package com.springbootdemo.member.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

/**
 * Created by zzf on 2017/9/6.
 */
@Entity
@Table(name = "users")
public class User  implements UserDetails {

    @Id
    private String username;
    private String password;
    private String enabled;
    private String service;
    @Transient
    private List<Authens> authenss;
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public List<Authens> getAuthenss() {
        return authenss;
    }
    public void setAuthenss(List<Authens> authenss) {
        this.authenss = authenss;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//取出此用户所有的角色
        if(authenss == null || authenss.size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(Authens role : authenss){
            commaBuilder.append(role.getAuthority()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
