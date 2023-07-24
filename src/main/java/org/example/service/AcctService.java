package org.example.service;

public interface AcctService {
    void trans(String acctNo,String transAcctNo, double amtNum);

    boolean setLoss(String acctNo,String opertion);

}
