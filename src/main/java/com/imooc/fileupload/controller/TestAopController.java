package com.imooc.fileupload.controller;


import com.imooc.fileupload.annotation.SysLog;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("test")
public class TestAopController {
    @GetMapping("/add")
    @SysLog(opration = "添加用户",module = "用户管理",description = "用户添加详情")
    public String addUser(String name,String password){
        System.out.println("添加用户");
        return "添加用户成功";
    }

   //使用map接受  前段提交的数据   注意 @requestBody  接受  json格式的数据
    //                               @requestParam 接受  标准表单提交格式
    //不加以上两个注解  获取不到数据
    @GetMapping("/demo")
    public <T> String getParamByMap(@RequestBody Map<String,T> map){
        map.forEach((key,value) ->{
            System.out.println(key);
            System.out.println(value);
        });
        return  "hello";
    }
    @PostMapping("/upload")
    public String upload(MultipartHttpServletRequest request){
        //获取上传的所有文件  key位<input type="file" name=""/> 中的name值   value位MultipartFile 对象
        Map<String, MultipartFile> fileMap = request.getFileMap();
        fileMap.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().getOriginalFilename());
        });
        //获取提交表单的所有普通字段
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((paramName,value)->{
            System.out.println(paramName);
            System.out.println(value);
        });
        return "success";
    }
}
