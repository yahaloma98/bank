package org.example.service.Impl;


import org.example.dao.CustDao;
import org.example.dao.Impl.CustDaoImpl;
import org.example.entity.CustInfo;
import org.example.service.CustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CustServiceImpl implements CustService {

    private final Logger logger = LoggerFactory.getLogger(CustDaoImpl.class);

    /**
     * 登录主账号进行网上银行申请
     *
     * @param acctNo
     * @param password
     * @return
     */
    public boolean isAcctNoLogin(String acctNo, String password) {
        CustDao custDao = new CustDaoImpl();
        try {
            List<Map<String, Object>> resultList = custDao.selectAcctNO(acctNo, password);
            if (resultList.size() == 0) {
                System.out.println("账号不存在！");
                throw new Exception("账号不存在！");
            }
            if (!resultList.get(0).get("acctNo").equals(acctNo)) {
                System.out.println("账号错误！");
                throw new Exception("账号错误！");
            }
            if (!resultList.get(0).get("password").equals(password)) {
                System.out.println("密码错误！");
                throw new Exception("密码错误!");
            }
            if (resultList.get(0).get("onlineNo")!=null) {
                System.out.println("已经申请过网上银行！");
                throw new Exception("已经申请过网上银行!");
            }
            custDao.updateOnlineNo(acctNo);
            //logger.debug("INFO: ", "网上银行申请成功!");
            return true;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }
        return false;
    }

    /**
     * @param custInfo
     */
    public boolean register(CustInfo custInfo) {
        CustDao custDao = new CustDaoImpl();
        try {
           //int res = custDao.insertCustomer(custInfo);
           //return res == 1;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }

        return false;
    }

    /**
     * 修改密码
     *
     * @param acctNo
     * @param password
     * @return
     */
    public boolean changePwd(String acctNo, String password) {
        CustDao custDao = new CustDaoImpl();
        try {
            boolean flag = custDao.updatePwd(acctNo, password);
            if (flag) logger.debug("INFO: ", "密码修改成功!");
            return flag;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return false;
    }

    @Override
    public boolean isLogin(String onlineNo, String password) {
        CustDao custDao = new CustDaoImpl();
        try {
            List<Map<String, Object>> resultList = custDao.selectOnlineNO(onlineNo, password);
            if (resultList.size() == 0) throw new Exception("网上银行账号不存在！");
            if (!resultList.get(0).get("onlineNo").equals(onlineNo)) throw new Exception("网上银行账号错误！");
            if (!resultList.get(0).get("password").equals(password)) throw new Exception("密码错误!");
            logger.debug("INFO: ", "网上银行登录成功!");
            return true;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }

        return false;
    }
}
