package org.example.service.Impl;


import org.example.dao.CustDao;
import org.example.dao.Impl.CustDaoImpl;
import org.example.entity.CustInfo;
import org.example.service.AcctService;
import org.example.service.CustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CustServiceImpl implements CustService {

    private final Logger logger = LoggerFactory.getLogger(CustDaoImpl.class);
    CustDao custDao = new CustDaoImpl();
    /**
     * 登录主账号进行网上银行申请
     *
     * @param acctNo
     * @param password
     * @return
     */
    public boolean isAcctNoLogin(String acctNo, String password) {
        try {
            List<Map<String, Object>> resultList = custDao.selectCustByAcctNo(acctNo);
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
            System.out.println("网上银行申请成功!");
            //logger.debug("INFO: ", "网上银行申请成功!");
            return true;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }
        return false;
    }

    /**
     * 修改密码
     * @param onlineNo
     * @param password
     * @return
     */
    public boolean changePwd(String onlineNo, String password) {

        try {
            boolean flag = custDao.updatePwd(onlineNo, password);
            if (flag) {
                System.out.println("密码修改成功！");
                logger.debug("INFO: ", "密码修改成功!");
            }
            return flag;
        } catch (Exception exception) {
            logger.error("ERROR: ", exception);
        }
        return false;
    }

    @Override
    public boolean setLoss(String onlineNo) {
        AcctService acctService = new AcctServiceImpl();
        try {
            List<Map<String, Object>> resultList = custDao.selectCustByOnlineNo(onlineNo);
            String acctNo = String.valueOf(resultList.get(0).get("acctNo"));
            if (resultList.get(0).get("isClosure").equals("1")){
                System.out.println("账号处于挂失状态，不用重复挂失！");
            }
            if (resultList.get(0).get("isClosure").equals("0")){
                custDao.updateIsClosure(onlineNo);
                acctService.setLoss(acctNo);
                System.out.println("挂失成功！");
            }

            return true;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }
        return false;
    }

    @Override
    public boolean isLogin(String onlineNo, String password) {
        try {
            List<Map<String, Object>> resultList = custDao.selectCustByOnlineNo(onlineNo);
            if (resultList.size() == 0) {
                System.out.println("网上银行账号不存在！");
                throw new Exception("网上银行账号不存在！");
            }
            if (!resultList.get(0).get("onlineNo").equals(onlineNo)) {
                System.out.println("网上银行账号错误！");
                throw new Exception("网上银行账号错误！");
            }
            if (!resultList.get(0).get("password").equals(password)) {
                System.out.println("密码错误！");
                throw new Exception("密码错误!");
            }
            System.out.println("网上银行登录成功!");
            //logger.debug("INFO: ", "网上银行登录成功!");
            return true;
        } catch (Exception exception) {
            //logger.error("ERROR: ", exception);
        }

        return false;
    }
}
