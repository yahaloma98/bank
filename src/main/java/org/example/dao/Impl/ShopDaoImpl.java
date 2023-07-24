package org.example.dao.Impl;

import org.example.dao.ShopDao;
import org.example.utils.JDBCDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ShopDaoImpl implements ShopDao {
    JDBCDao jdbcDao = new JDBCDao();
    private final Logger logger = LoggerFactory.getLogger(ShopDao.class);
    @Override
    public List<Map<String, Object>> selectAllProduct() {
        String sql = "select * from shopinfo";
        try{
            List<Map<String, Object>> selectPrd = jdbcDao.select(sql);
            return selectPrd;
        }catch(Exception e){
            logger.error("ERROR: ", e);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectPrdByItemNo(String itemNo) {
        String sql = "select * from shopinfo where itemNo = '" + itemNo + "'";
        try {
            List<Map<String,Object>> prdInfo = jdbcDao.select(sql);
            return prdInfo;

        }catch (Exception e){
            logger.error("ERROR: ", e);
        }
        return null;
    }

    @Override
    public double selectPriceByItemNo(String itemNo) {
        String sql = "select price from shopinfo where itemNo = '" + itemNo + "'";
        try{
            List<Map<String, Object>> select = jdbcDao.select(sql);
            double res= Double.parseDouble(select.get(0).get("price").toString());
            return res;
        }catch (Exception e){
            logger.error("Error:",e);
        }
        return 0;
    }
}
