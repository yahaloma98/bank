package org.example.service.Impl;

import org.example.dao.AcctDao;
import org.example.dao.CustDao;
import org.example.dao.Impl.AcctDaoImpl;
import org.example.dao.Impl.CustDaoImpl;
import org.example.dao.Impl.ShopDaoImpl;
import org.example.dao.ShopDao;
import org.example.service.ShopService;

import java.util.List;
import java.util.Map;

public class ShopServiceImpl implements ShopService {
    ShopDao shopDao = new ShopDaoImpl();
    AcctDao acctDao = new AcctDaoImpl();
    CustDao custDao = new CustDaoImpl();
    @Override
    public List<Map<String, Object>> getPrdList() {
        return shopDao.selectAllProduct();
    }

    private boolean isValidProductId(String itemNo){
        List<Map<String, Object>>  prdInfo = shopDao.selectPrdByItemNo(itemNo);
        return prdInfo!=null;
    }

    @Override
    public List<Map<String, Object>> selectPrd(String itemNo) {

        return shopDao.selectPrdByItemNo(itemNo);
    }

    @Override
    public void purchaseprd(String itemNo,String onlineNo) {
        String acctNo =
        if (isValidProductId(itemNo)){
            if (shopDao.selectPriceByItemNo(itemNo)<=acctDao.getAccBalance(acctNo)){
                System.out.println("购买成功");
                acctDao.updateAcct(acctNo,shopDao.selectPriceByItemNo(itemNo),"jian");
                System.out.println("现在该账户的余额为"+acctDao.getAccBalance(acctNo));
            }else {
                System.out.println("你没有足够的钱！");
            }
        }else{
            System.out.println("商品不存在或无效的itemNo");
        }
    }
}
