package org.example.dao;

import java.util.List;
import java.util.Map;

public interface AcctDao {
    List<Map<String, Object>> selectAcct(String acctNo);

    boolean updateAcct(String acctNo,double amtNum,String opertion);

    boolean updateIsClosure(String acctNo);

}
