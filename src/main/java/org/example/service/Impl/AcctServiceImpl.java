package org.example.service.Impl;


import org.example.dao.AcctDao;
import org.example.dao.Impl.AcctDaoImpl;
import org.example.service.AcctService;

public class AcctServiceImpl implements AcctService {

    @Override
    public void trans(String acctNo,String transAcctNo, double amtNum) {
        AcctDao acctDao = new AcctDaoImpl();
        try {
            acctDao.updateAcct(acctNo,amtNum,"jian");
            acctDao.updateAcct(transAcctNo,amtNum,"add");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
