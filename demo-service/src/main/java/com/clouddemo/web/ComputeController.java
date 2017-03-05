package com.clouddemo.web;

import com.clouddemo.common.sysuser.entity.SysUser;
import com.clouddemo.demoservice.sysuser.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/10.
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @Resource
    private ISysUserService sysUserService;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        sysUserService.findSysUser(1);
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @RequestMapping(value = "/findSysUserPage",method = RequestMethod.GET)
    public Map<String, Object> findSysUserPage(@RequestParam PageRequest pageRequest, @RequestParam String searchUser){
        Page<SysUser> userPage = sysUserService.findSysUserPage(pageRequest, searchUser);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("rows",userPage.getContent());
        modelMap.put("total",userPage.getTotalElements());
        return modelMap;
    }
}
