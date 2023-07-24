package org.example.dao.Impl;

import org.example.dao.SerialDao;
import org.example.utils.JDBCDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
