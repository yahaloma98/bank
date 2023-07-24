package org.example.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustInfo {
    //账户
    private String acctNo;
    //客户名
    private String custName;
    //身份证号
    private String credCardNo;
    //地址
    private String address;
    //电话号码
    private String telephone;
    //账户状态
    private String isClosure;
    //密码
    private String password;
    //网上银行账号
    private String onlineNo;
}
