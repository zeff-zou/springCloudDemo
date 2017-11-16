package com.clouddemo.web;


import com.clouddemo.rabbit.Sender;
import com.clouddemo.service.ComputeClient;
import com.clouddemo.service.ConsumerService;
import com.clouddemo.vo.SysUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zzf on 2017/2/10.
 */
@RefreshScope
@RestController
public class ConsumerController {
    @Autowired
    ComputeClient computeClient;
    @Autowired
    Sender sender;
    @Autowired
    ConsumerService consumerService;
    @Value("${from}")
    private String from;
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return computeClient.add(10, 20);
    }
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping(value = "/findSysUserPage",method = RequestMethod.GET)
    public  Map<String, Object> findSysUserPage(){
//        PageRequest pageRequest = new PageRequest(offset/limit,limit,pageable.getSort());
//        Page<SysUser> userPage = computeClient.findSysUserPage(pageRequest, searchUser);
        Map<String, Object> modelMap = new HashMap<String, Object>();
//        modelMap.put("rows",userPage.getContent());
//        modelMap.put("total",userPage.getTotalElements());
        modelMap.put("draw" , 1);
        modelMap.put("recordsTotal" , 50);
        modelMap.put("recordsFiltered" , 50);
        List<SysUser> sysUserList = new ArrayList<SysUser>();
        for (int i=0;i<10;i++){
            SysUser sysUser = new SysUser();
            sysUser.setId(i);
            sysUser.setFirstName("aaa"+i);
            sysUser.setLastName("bbb"+i);
            sysUserList.add(sysUser);
        }
        modelMap.put("data" , sysUserList);
        return modelMap;
    }
    @RequestMapping(value = "/sendMq",method = RequestMethod.GET)
    public String sendMq() {
        sender.send();
        return "success";
    }

    @PostMapping("/login")
    public Object login(String username, String password) throws Exception {
        return consumerService.login(username,password);
    }
}
