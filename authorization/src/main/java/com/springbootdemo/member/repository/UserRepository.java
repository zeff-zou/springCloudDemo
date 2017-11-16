package com.springbootdemo.member.repository;

import com.springbootdemo.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zzf on 2017/9/6.
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
