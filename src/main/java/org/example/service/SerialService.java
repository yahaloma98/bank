package org.example.service;

public interface SerialService {
    void trans(String onlineNo,String transAcctNo, double amtNum);

    void findTrans(String onlineNo);
}
