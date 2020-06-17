package com.cmj.blog.doamin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
public class ResultLog {
    private String url;  //请求地址
    private String ip;   //请求ip
    private String classMethod; //类 方法
    private Object[] args;  //请求参数



}
