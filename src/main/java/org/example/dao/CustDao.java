package org.example.dao;

import org.example.entity.CustInfo;

import java.util.List;
import java.util.Map;

public interface CustDao {

    List<Map<String, Object>> selectCustByAcctNo(String acctNo);

    List<Map<String, Object>> selectCustByOnlineNo(String onlineNo);

    List<Map<String, Object>> selectOnlineNO(String onlineNo, String password);
    boolean updateOnlineNo(String acctNo);

    boolean updatePwd(String acctNo,String password);

}
