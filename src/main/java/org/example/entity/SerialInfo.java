package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SerialInfo {
    //流水号
    private String serialNo;
    //账号
    private String acctNo;
    //到账账户
    private String transAcctNo;
    //操作类型
    private String operType;
    //交易金额
    private String amtNum;
}
