package org.example.dao.Impl;

import org.example.dao.AcctDao;
import org.example.dao.JDBCDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AcctDaoImpl implements AcctDao {
    private final Logger logger = LoggerFactory.getLogger(CustDaoImpl.class);
    JDBCDao jdbcDao = new JDBCDao();
    @Override
    public List<Map<String, Object>> selectAcct(String acctNo) {
        String sql = "SELECT * FROM acctinfo WHERE acctNo = '" + acctNo + "'" + " and type = 'hq'";
        try {
            List<Map<String, Object>> resultList = jdbcDao.select(sql);
            return resultList;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }
        return null;
    }

    @Override
    public boolean updateAcct(String number, double amtNum,String opertion) {
        String sql = null;
        try {
            if (opertion.equals("add")){
                sql= "update acctinfo set aumbal = aumbal+"+ amtNum + " where acctNo = '" + number + "' and type = 'hq'";
            }
            if (opertion.equals("jian")){
                sql= "update acctinfo set aumbal = aumbal-"+ amtNum + " where acctNo = '" + number + "' and type = 'hq'";
            }
            jdbcDao.insertOrDeleteOrUpdate(sql);
            return true;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }
        return false;
    }

    @Override
    public boolean updateIsClosure(String acctNo,String opertion) {
        String sql = "update acctinfo set isClosure = '"+ opertion +"' where acctNo = '" + acctNo + "'";
        try {
            jdbcDao.insertOrDeleteOrUpdate(sql);
            return true;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return false;
    }
}
