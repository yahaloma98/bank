package org.example.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AcctInfo {
    //账号
    private String acctNo;
    //客户名
    private String subAcctNo;
    //密码
    private String password;
    //子账户类型
    private String type;
    //子账户状态
    private String isClosure;
    //余额
    private double aumbal;
    //存款时间
    private Date depositDate;
    //定期到期时间
    private Date lastDate;

}
