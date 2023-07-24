package org.example.dao;

import java.util.List;
import java.util.Map;

public interface SerialDao {
    int insertTrans(String acctNo,String transAcctNo,double amtNum);

    List<Map<String, Object>> selectSerialByAcctNo(String acctNo);


}
