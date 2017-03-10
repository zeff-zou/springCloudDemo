package com.clouddemo.demoservice.sysuser.service.impl;

import com.clouddemo.common.base.service.impl.AbstractBaseService;
import com.clouddemo.common.sysuser.entity.SysUser;
import com.clouddemo.demoservice.sysuser.repostory.SysUserRepository;
import com.clouddemo.demoservice.sysuser.service.ISysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zzf on 2017/2/14.
 */
@Service
public class SysUserServiceImpl extends AbstractBaseService<SysUser,Integer> implements ISysUserService {

    @SuppressWarnings("unused")
    private SysUserRepository getSysUserRepository() {
        return (SysUserRepository) baseRepository;
    }

    @Override
    public SysUser findSysUser(Integer id) {
        return getSysUserRepository().findOne(id);
    }

    @Override
    @Transactional
    public String addSysUser(SysUser sysUser) {
        return "success";
    }

    @Override
    @Transactional
    public void deleteSysUser(Integer id) {
    }

    @Override
    @Transactional
    public String updateSysUser(SysUser sysUser) {
        return "success";
    }

    @Override
    public SysUser loginSysUser(String loginId, String password) {
        return null;
    }

    @Override
    public Page<SysUser> findSysUserPage(Pageable pageable, String searchUser) {

        return getSysUserRepository().query(pageable,searchUser);
    }

    @Override
    @Transactional
    public void alertPsw(Integer id, String userPsw) {
        SysUser user = getSysUserRepository().getOne(id);

    }


}
