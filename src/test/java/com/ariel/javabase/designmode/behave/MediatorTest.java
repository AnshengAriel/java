package com.ariel.javabase.designmode.behave;

import org.junit.Test;

/**
 * 中介者模式
 */
public class MediatorTest {

    @Test
    public void testMediator() {
        ConcreteMediator concreteMediator = new ConcreteMediator();
        //让两个具体同事类人数中介者对象
        ConcreteColleague1 concreteColleague1 = new ConcreteColleague1(concreteMediator);
        ConcreteColleague2 concreteColleague2 = new ConcreteColleague2(concreteMediator);

        //让这接着认识各个具体同事类对象
        concreteMediator.setConcreteColleague1(concreteColleague1);
        concreteMediator.setConcreteColleague2(concreteColleague2);

        //具体同事类对象的发送消息都是通过中介者转发
        concreteColleague1.send("你在干什么？");
        concreteColleague2.send("我不想告诉你！");
    }


    /**
     * 抽象同事类
     */
    abstract static class Colleague {
        protected Mediator mediator;
        public Colleague(Mediator mediator){
            this.mediator = mediator;
        }

        abstract void send(String message);

        abstract void notify(String message);
    }

    /**
     * 同事对象1
     **/
    static class ConcreteColleague1 extends Colleague {

        public ConcreteColleague1(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void send(String message) {
            mediator.send(message, this);
        }

        @Override
        public void notify(String message) {
            System.out.println("同事1得到消息:" + message);
        }
    }

    /**
     * 同事对象2
     **/
    static class ConcreteColleague2 extends Colleague {

        public ConcreteColleague2(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void send(String message) {
            mediator.send(message, this);
        }

        @Override
        public void notify(String message) {
            System.out.println("同事2得到消息:" + message);
        }
    }

    /**
     * 抽象中介者接口
     **/
    interface Mediator {
        void send(String message,Colleague colleague);
    }


    /**
     * 具体中介者类
     **/
    static class ConcreteMediator implements Mediator {
        private ConcreteColleague1 concreteColleague1;
        private ConcreteColleague2 concreteColleague2;
        public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
            this.concreteColleague1 = concreteColleague1;
        }

        public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
            this.concreteColleague2 = concreteColleague2;
        }

        @Override
        public void send(String message, Colleague colleague) {
            if (colleague == concreteColleague1) {
                concreteColleague2.notify(message);
            } else {
                concreteColleague1.notify(message);
            }
        }

    }

}



