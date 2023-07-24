package org.example.service.Impl;

import org.example.dao.AcctDao;
import org.example.dao.CustDao;
import org.example.dao.Impl.AcctDaoImpl;
import org.example.dao.Impl.CustDaoImpl;
import org.example.dao.Impl.SerialDaoImpl;
import org.example.dao.SerialDao;
import org.example.service.AcctService;
import org.example.service.SerialService;

import java.util.List;
import java.util.Map;

public class SerialServiceImpl implements SerialService {
    CustDao custDao = new CustDaoImpl();
    SerialDao serialDao = new SerialDaoImpl();

    AcctDao acctDao = new AcctDaoImpl();
    AcctService acctService = new AcctServiceImpl();
    @Override
    public void trans(String onlineNo, String transAcctNo, double amtNum) {
        try {
            List<Map<String, Object>> resultList = custDao.selectCustByOnlineNo(onlineNo);
            if (resultList.get(0).get("isClosure").equals("1")){
                System.out.println("账号处于挂失状态，无法转账！");
            }
            if (resultList.get(0).get("isClosure").equals("0")){
                String acctNo = String.valueOf(resultList.get(0).get("acctNo"));
                List<Map<String, Object>> resultList1 = custDao.selectCustByAcctNo(transAcctNo);
                if (resultList1.size() == 0) {
                    System.out.println("收款账号不存在！");
                }else {
                    List<Map<String, Object>> resultList2 = acctDao.selectAcct(acctNo);
                    double aumbal = (double) resultList2.get(0).get("aumbal");
                    if (aumbal < amtNum){
                        System.out.println("余额不足，转账失败！");
                    }else {
                        serialDao.insertTrans(acctNo,transAcctNo,amtNum);
                        acctService.trans(acctNo,transAcctNo,amtNum);
                        System.out.println("转账成功！");
                    }
                }
            }

        } catch (Exception e) {
           // throw new RuntimeException(e);
        }

    }


    @Override
    public void findTrans(String onlineNo) {

        try {
            List<Map<String, Object>> resultList =  custDao.selectCustByOnlineNo(onlineNo);
            String acctNo = String.valueOf(resultList.get(0).get("acctNo"));
            List<Map<String, Object>> resultList1 = serialDao.selectSerialByAcctNo(acctNo);
            for (int i = 0; i < resultList1.size(); i++) {

                System.out.println("交易时间："+resultList1.get(i).get("operDate")+" 收款账号："+resultList1.get(i).get("transAcctNo")
                +" 交易金额："+resultList1.get(i).get("amtNum"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
