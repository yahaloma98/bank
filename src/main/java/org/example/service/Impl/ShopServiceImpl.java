package org.example.service.Impl;

import org.example.dao.AcctDao;
import org.example.dao.CustDao;
import org.example.dao.Impl.AcctDaoImpl;
import org.example.dao.Impl.CustDaoImpl;
import org.example.dao.Impl.ShopDaoImpl;
import org.example.dao.ShopDao;
import org.example.service.AcctService;
import org.example.service.ShopService;

import java.util.List;
import java.util.Map;

public class ShopServiceImpl implements ShopService {
    ShopDao shopDao = new ShopDaoImpl();
    AcctDao acctDao = new AcctDaoImpl();
    CustDao custDao = new CustDaoImpl();
    AcctService acctService = new AcctServiceImpl();

    @Override
    public void getPrdList() {
        List<Map<String, Object>> resultList = shopDao.selectAllProduct();
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("商品编号："+resultList.get(i).get("itemNo")+" 商品名："+resultList.get(i).get("productName")
                    +" 商品价格："+resultList.get(i).get("price"));
        }
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
        List<Map<String,Object>> map=custDao.selectCustByOnlineNo(onlineNo);
        String accno = String.valueOf(map.get(0).get("acctNo"));
        List<Map<String, Object>> acctNo = acctDao.selectAcct(accno);
        double aumbal = (double) acctNo.get(0).get("aumbal");
        if (isValidProductId(itemNo)){
            if (shopDao.selectPriceByItemNo(itemNo)<=aumbal){

                acctDao.updateAcct(accno,shopDao.selectPriceByItemNo(itemNo),"jian");
//                System.out.println("现在该账户的余额为"+acctDao.getAccBalance(acctNo));
                System.out.println("购买成功");
            }else {
                System.out.println("你没有足够的钱！");
            }
        }else{
            System.out.println("商品不存在或无效的itemNo");
        }
    }
}
