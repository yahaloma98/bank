package org.example.service.Impl;


import org.example.dao.AcctDao;
import org.example.dao.Impl.AcctDaoImpl;
import org.example.service.AcctService;

import java.util.List;
import java.util.Map;

public class AcctServiceImpl implements AcctService {
    AcctDao acctDao = new AcctDaoImpl();
    @Override
    public void trans(String acctNo,String transAcctNo, double amtNum) {

        try {
            acctDao.updateAcct(acctNo,amtNum,"jian");
            acctDao.updateAcct(transAcctNo,amtNum,"add");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setLoss(String acctNo,String opertion) {
        try {
            acctDao.updateIsClosure(acctNo,opertion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return false;
    }
}
