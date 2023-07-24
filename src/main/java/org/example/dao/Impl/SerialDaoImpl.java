package org.example.dao.Impl;

import org.example.dao.SerialDao;
import org.example.dao.JDBCDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SerialDaoImpl implements SerialDao {
    private final Logger logger = LoggerFactory.getLogger(CustDaoImpl.class);
    JDBCDao jdbcDao = new JDBCDao();
    @Override
    public int insertTrans(String acctNo, String transAcctNo,double amtNum) {
        Date date = new Date();//获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//定义日期格式
        String operDate = dateFormat.format(date);//将时间格式化为字符串
        String sql = "INSERT into serialinfo(acctNo,transAcctNo,operType,amtNum,operDate) values("+
                "'"+acctNo+"','"+transAcctNo+"','3','"+amtNum+"','"+operDate+"');";
        try {
            return jdbcDao.insertOrDeleteOrUpdate(sql);
        } catch (Exception exception) {
           // logger.error("ERROR: ", exception);
        }
        return -1;
    }

    @Override
    public List<Map<String, Object>> selectSerialByAcctNo(String acctNo) {
        String sql = "SELECT * FROM serialinfo WHERE acctNo = '" + acctNo + "'";
        try {
            List<Map<String, Object>> resultList = jdbcDao.select(sql);
            return resultList;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return null;
    }
}
