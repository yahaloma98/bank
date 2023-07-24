package org.example.boot;


import org.example.dao.Impl.AcctDaoImpl;
import org.example.dao.Impl.CustDaoImpl;
import org.example.dao.Impl.SerialDaoImpl;
import org.example.dao.Impl.ShopDaoImpl;
import org.example.service.Impl.CustServiceImpl;
import org.example.service.Impl.SerialServiceImpl;
import org.example.service.Impl.ShopServiceImpl;
import org.example.service.SerialService;
import org.example.service.ShopService;

import java.util.Scanner;

public class Main extends ShopDaoImpl {
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==============================");
            System.out.println("1.申请网上银行");
            System.out.println("2.登录");
            System.out.println("3.退出");
            System.out.print("请输入序号：");
            int choiceInt = sc.nextInt();
            switch (choiceInt) {
                case 1:
                    main.applyOnlineBank();
                    break;
                case 2:
                    main.login();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
    public void applyOnlineBank(){
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");
        System.out.print("请输入账号：");
        String acctNo = sc.next();
        System.out.print("请输入密码：");
        String password = sc.next();
        CustServiceImpl custService = new CustServiceImpl();
        try {
            custService.isAcctNoLogin(acctNo,password);
        } catch (Exception exception) {
        }
    }
    public void login(){
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");
        System.out.print("请输入网上银行账号：");
        String onlineNo = sc.next();
        System.out.print("请输入密码：");
        String password = sc.next();
        CustServiceImpl custService = new CustServiceImpl();
        try {
            if (custService.isLogin(onlineNo, password)) {
                main.mainMenu(onlineNo);
            }
        } catch (Exception exception) {
        }
    }

    public void mainMenu(String onlineNo){
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        SerialService serialservice = new SerialServiceImpl();
        System.out.println("==============================");
        while (true) {
            System.out.println("1.查询一卡通信息");
            System.out.println("2.转账汇款");
            System.out.println("3.网上购物");
            System.out.println("4.退出");
            System.out.print("请输入序号：");
            int choiceInt = sc.nextInt();
            switch (choiceInt) {
                case 1:
                    main.cardMenu(onlineNo);
                    break;
                case 2:
                    System.out.println("==============================");
                    System.out.println("请输入收款人账号");
                    String transAcctNo = sc.next();
                    System.out.println("请输入转账金额");
                    double amtNum = sc.nextDouble();
                    serialservice.trans(onlineNo,transAcctNo,amtNum);
                    break;
                case 3:
                    main.shopMenu(onlineNo);
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }
    public void cardMenu(String onlineNo){
        Scanner sc = new Scanner(System.in);
        SerialService serialservice = new SerialServiceImpl();
        CustServiceImpl custService = new CustServiceImpl();
        System.out.println("==============================");
        while (true) {
            System.out.println("1.查询交易记录");
            System.out.println("2.修改密码");
            System.out.println("3.挂失");
            System.out.println("4.补办");
            System.out.println("5.退出");
            System.out.print("请输入序号：");
            int choiceInt = sc.nextInt();
            switch (choiceInt){
                case 1:
                    serialservice.findTrans(onlineNo);
                    break;
                case 2:
                    System.out.println("==============================");
                    System.out.println("请输入新密码：");
                    String password = sc.next();
                    custService.changePwd(onlineNo,password);
                    break;
                case 3:
                    custService.setLoss(onlineNo);
                    break;
                case 4:
                    custService.reissue(onlineNo);
                    break;
                case 5:
                    return;
                default:break;
            }
        }

    }
    public void shopMenu(String onlineNo) {
        ShopService ss = new ShopServiceImpl();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==============================");
            System.out.println("1.查询产品列表");
            System.out.println("2.购买产品");
            System.out.println("3.退出");
            System.out.print("请输入序号：");
            int choiceInt = sc.nextInt();
            switch (choiceInt) {
                case 1:
                    ss.getPrdList();
                    break;
                case 2:
                    System.out.println("请输入想要购买的商品编号：");
                    String next = sc.next();
                    ss.purchaseprd(next, onlineNo);
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
