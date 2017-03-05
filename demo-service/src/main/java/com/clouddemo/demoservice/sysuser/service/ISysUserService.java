package com.clouddemo.demoservice.sysuser.service;

import com.clouddemo.common.base.service.IBaseService;
import com.clouddemo.common.sysuser.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zzf on 2017/2/14.
 */
public interface ISysUserService extends IBaseService<SysUser, Integer> {
    SysUser findSysUser(Integer id);

    String addSysUser(SysUser sysUser);

    void deleteSysUser(Integer id);

    String updateSysUser(SysUser sysUser);

    SysUser loginSysUser(String loginId, String password);

    Page<SysUser> findSysUserPage(Pageable pageable, String searchUser);

    void alertPsw(Integer id ,String userPsw);
}
