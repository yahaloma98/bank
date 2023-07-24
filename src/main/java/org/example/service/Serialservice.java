package org.example.service;

public interface Serialservice {
    void trans(String onlineNo,String transAcctNo, double amtNum);

    void findTrans(String onlineNo);
}
