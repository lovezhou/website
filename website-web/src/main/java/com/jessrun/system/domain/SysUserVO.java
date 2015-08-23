package com.jessrun.system.domain;

import java.util.Date;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysUserVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//
    private String name;//姓名
    private String loginAccount;//登录账户
    private String loginPasswd;//登录密码
    private String email;//
    private String address;//
    private String phoneNum;//
    private String accountId;//身份证号
    
    
}
