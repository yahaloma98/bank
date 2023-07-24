package org.example.service;

import org.example.entity.CustInfo;

public interface CustService {

    boolean isAcctNoLogin(String acctNo, String password);

    boolean isLogin(String onlineNo, String password);

    boolean changePwd(String onlineNo,String password);

    boolean setLoss(String onlineNo);

    boolean reissue(String onlineNo);
}
