package org.example.service;

import org.example.entity.CustInfo;

public interface CustService {

    boolean isAcctNoLogin(String acctNo, String password);

    boolean isLogin(String onlineNo, String password);

    boolean register(CustInfo custInfo);

    boolean changePwd(String acctNo,String password);
}
