package org.example.service;

import java.util.List;
import java.util.Map;

public interface ShopService {
    void getPrdList();

    List<Map<String, Object> >selectPrd(String itemNo);
    void purchaseprd(String itemNo,String onlineNo);
}
