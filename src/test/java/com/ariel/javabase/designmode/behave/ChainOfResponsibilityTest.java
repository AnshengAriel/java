package com.ariel.javabase.designmode.behave;

import org.junit.Test;

import java.util.Objects;

/**
 * 责任链模式
 */
public class ChainOfResponsibilityTest {

    @Test
    public void testPreSingleTon() {
        GroupLeader groupLeader = new GroupLeader();
        Manager manager = new Manager();
        CFO cfo = new CFO();

        groupLeader.setNextHandler(manager);
        manager.setNextHandler(cfo);

        groupLeader.handle(1000);
    }


    /**
     * 责任链抽象类
     */
    interface BudgetHandler {
        void setNextHandler(BudgetHandler nextHandler);
        boolean handle(int amount);
    }

    /**
     * 组长
     */
    static class GroupLeader implements BudgetHandler {
        private BudgetHandler nextHandler;

        @Override
        public void setNextHandler(BudgetHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public boolean handle(int amount) {
            Objects.requireNonNull(nextHandler);
            if(amount<1000){
                System.out.println("小钱，批了！");
                return true;
            }
            System.out.println(String.format("%d超出GroupLeader权限,请更高级管理层批复",amount));
            return nextHandler.handle(amount);
        }
    }

    /**
     * 经理
     */
    static class Manager implements BudgetHandler {
        private BudgetHandler nextHandler;

        @Override
        public void setNextHandler(BudgetHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public boolean handle(int amount) {
            Objects.requireNonNull(nextHandler);
            if(amount<5000){
                System.out.println("小于2000块，我这个经理可以决定：同意！");
                return true;
            }
            System.out.println(String.format("%d超出Manager权限,请更高级管理层批复",amount));
            return nextHandler.handle(amount);
        }
    }

    /**
     * 首席财务官
     */
    static class CFO implements BudgetHandler {
        private BudgetHandler nextHandler;

        @Override
        public void setNextHandler(BudgetHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public boolean handle(int amount) {
            if(amount<50000){
                System.out.println("CFO同意,希望你再接再厉，为公司做出更大的贡献。");
                return true;
            }
            if (nextHandler!=null){
                return nextHandler.handle(amount);
            }
            //已经没有更高级的管理层来处理了
            System.out.println(String.format("%d太多了，回去好好看看能不能缩减一下",amount));
            return false;
        }
    }


}

