package org.example.service;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<Map<String,Object>> getPrdList();

    List<Map<String, Object> >selectPrd(String itemNo);
    void purchaseprd(String itemNo,String onlineNo);
}
