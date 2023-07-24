package org.example.dao;

import java.util.List;
import java.util.Map;

public interface ShopDao {
    List<Map<String,Object>> selectAllProduct();
    List<Map<String,Object> >selectPrdByItemNo(String itemNo);
    double selectPriceByItemNo(String itemNo);
}
