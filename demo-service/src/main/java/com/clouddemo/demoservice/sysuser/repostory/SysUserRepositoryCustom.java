package com.clouddemo.demoservice.sysuser.repostory;

import com.clouddemo.common.sysuser.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zzf on 2017/2/14.
 */
public interface SysUserRepositoryCustom {
    Page<SysUser> query(Pageable pageable, String searchUser);
}
