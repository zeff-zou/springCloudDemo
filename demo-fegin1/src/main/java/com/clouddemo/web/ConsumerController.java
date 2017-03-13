package com.clouddemo.web;


import com.clouddemo.rabbit.Sender;
import com.clouddemo.service.ComputeClient;
import com.clouddemo.vo.RowData;
import com.clouddemo.vo.SysUser;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;


/**
 * Created by zzf on 2017/2/10.
 */
@RefreshScope
@RestController
public class ConsumerController {
    @Autowired
    ComputeClient computeClient;

    @Autowired
    private Sender sender;

    @Value("${from}")
    private String from;
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(SysUser sysUser) {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return computeClient.add(10, 20);
    }
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }


    @RequestMapping(value = "/findSysUserPage",method = RequestMethod.POST)
    public Map<String, Object> findSysUserPage(Integer draw,Integer start,Integer length,String name,HttpServletRequest request, HttpServletResponse response){
        System.out.println("当前页数"+(start==0?1:(start/length+1))+";name="+name);
        String s = request.getParameter("order[0][column]");
        System.out.println(request.getParameter("columns["+s+"][data]"));
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("draw" , draw);
        modelMap.put("recordsTotal" , 50);
        modelMap.put("recordsFiltered" , 50);
        List<SysUser> sysUserList = new ArrayList<SysUser>();
        for (int i=10;i<20;i++){
            SysUser sysUser = new SysUser();
            sysUser.setId(i);
            sysUser.setFirstName("aaa"+i);
            sysUser.setLastName("bbb"+i);
            sysUser.setDT_RowId("row_"+i);
            RowData r = new RowData();
            r.setPkey(i);
            sysUser.setRowData(r);
            sysUserList.add(sysUser);
        }
        modelMap.put("data" , sysUserList);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return modelMap;
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public Map<String, Object> addOrUpdate(SysUser sysUser,  @RequestParam String myCroppedImage, HttpServletResponse response) throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String dataPrix = "";
        String data = "";
        String [] d = myCroppedImage.split("base64,");
        if(d != null && d.length == 2){
            dataPrix = d[0];
            data = d[1];
        }else{
            throw new Exception("上传失败，数据不合法");
        }

        String suffix = "";
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else{
            throw new Exception("上传图片格式不合法");
        }
        UUID uuid = UUID.randomUUID();
        String tempFileName = uuid + suffix;
        byte[] bs = Base64Utils.decodeFromString(data);
        try{
            //使用apache提供的工具类操作流
            FileUtils.writeByteArrayToFile(new File("E:\\cloudDemo\\demo-fegin1\\src\\main\\resources\\image", tempFileName), bs);
        }catch(Exception ee){
            throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
        }

        modelMap.put("tempFileName", tempFileName);
        modelMap.put("success","success");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return modelMap;
    }

    @RequestMapping(value = "/sendMq",method = RequestMethod.GET)
    public String sendMq() {
        sender.send();
        return "success";
    }
    @RequestMapping(value = "/sendHello",method = RequestMethod.GET)
    public String sendHello() {
        sender.sendHello();
        return "success";
    }
}
