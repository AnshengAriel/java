package com.ariel.java.base.designmode.behave;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * 观察者模式
 */
public class ObserverTest {

    @Test
    public void testObserver() {
        WechatServer server = new WechatServer();

        Observer userZhang = new UserObserver("ZhangSan");
        Observer userLi = new UserObserver("LiSi");
        Observer userWang = new UserObserver("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfomation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfomation("JAVA是世界上最好用的语言！");

    }


    interface Observer {
        void update(String message);
    }

    static class UserObserver implements Observer {

        private String name;
        private String message;

        public UserObserver(String name) {
            this.name = name;
        }

        @Override
        public void update(String message) {
            this.message = message;
            read();
        }

        public void read() {
            System.out.println(name + " 收到推送消息： " + message);
        }

    }

    interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObserver();
    }

    static class WechatServer implements Subject {

        private List<Observer> list;
        private String message;

        public WechatServer() {
            list = new ArrayList<Observer>();
        }

        @Override
        public void registerObserver(Observer o) {
            // TODO Auto-generated method stub
            list.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            // TODO Auto-generated method stub
            if (!list.isEmpty()) {
                list.remove(o);
            }
        }

        @Override
        public void notifyObserver() {
            // TODO Auto-generated method stub
            for (Observer o : list) {
                o.update(message);
            }
        }

        public void setInfomation(String s) {
            this.message = s;
            System.out.println("微信服务更新消息： " + s);
            // 消息更新，通知所有观察者
            notifyObserver();
        }

    }
}
