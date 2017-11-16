package com.springbootdemo.member.repository;

import com.springbootdemo.member.domain.Authens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zzf on 2017/9/6.
 */
@Repository
public interface AuthensRepository  extends JpaRepository<Authens, String> {

    /**
     * 根据用户查询角色
     * @param username
     * @return
     */
    List<Authens> findByUsername(String username);
}
