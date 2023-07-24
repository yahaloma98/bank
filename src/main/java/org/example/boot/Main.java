package org.example.boot;


import org.example.service.Impl.CustServiceImpl;
import org.example.service.Impl.SerialserviceImpl;
import org.example.service.Serialservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
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
        Scanner sc = new Scanner(System.in);
        Serialservice serialservice = new SerialserviceImpl();
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
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }
}
