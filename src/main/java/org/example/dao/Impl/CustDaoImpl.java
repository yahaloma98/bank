package org.example.dao.Impl;

import org.example.dao.CustDao;
import org.example.entity.CustInfo;
import org.example.utils.JDBCDao;
import org.example.utils.NoGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CustDaoImpl implements CustDao {

    private final Logger logger = LoggerFactory.getLogger(CustDaoImpl.class);
    JDBCDao jdbcDao = new JDBCDao();
    public List<Map<String, Object>> selectCustByAcctNo(String acctNo) {
        //JDBCDao jdbcDao = new JDBCDao();
        //sql语句
        String sql = "SELECT * FROM custinfo WHERE acctNo = '" + acctNo + "'";
        try {
            List<Map<String, Object>> resultList = jdbcDao.select(sql);
            return resultList;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectCustByOnlineNo(String onlineNo) {
        String sql = "SELECT * FROM custinfo WHERE onlineNo = '" + onlineNo + "'";
        try {
            List<Map<String, Object>> resultList = jdbcDao.select(sql);
            return resultList;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return null;
    }

    @Override
    public boolean updateOnlineNo(String acctNo) {
        //JDBCDao jdbcDao = new JDBCDao();
        NoGenerate noGenerate = new NoGenerate();
        String onlineNo = noGenerate.onlineNoGenerate();
        String sql = "update custinfo set onlineNo = '"
                + onlineNo + "' where acctNo = '" + acctNo + "'";
        try {
            jdbcDao.insertOrDeleteOrUpdate(sql);
            return true;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return false;
    }

    public boolean updatePwd(String acctNo, String password) {
        //JDBCDao jdbcDao = new JDBCDao();
        String sql = "update custinfo set password = '"
                + password + "' where acctNo = '" + acctNo + "'";
        try {
            jdbcDao.insertOrDeleteOrUpdate(sql);
            return true;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return false;
    }
}
