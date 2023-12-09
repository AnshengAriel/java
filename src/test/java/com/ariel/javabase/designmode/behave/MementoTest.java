package com.ariel.javabase.designmode.behave;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * 备忘录模式
 */
public class MementoTest {

    @Test
    public void testPreSingleTon() {
        //创建原发器，设置状态
        Originator originator = new Originator();
        originator.setState("草稿");
        System.out.println("合同状态：" + originator.getState());

        //管理者使用备忘录记录状态
        Caretaker caretaker = new Caretaker();
        caretaker.saveMemento(originator.createMemento());

        //修改原发器的状态
        originator.setState("执行中");
        System.out.println("合同状态：" + originator.getState());

        //恢复备忘录中存的状态
        originator.restoreMemento(caretaker.retriveMemento());
        System.out.println("合同状态：" + originator.getState());
    }


    /**
     * @description:窄接口，无任何方法
     */
    interface Memento {
    }

    /**
     * @description:原发器（发起人）
     */
    @Data
    static class Originator {

        /**
         * 状态
         */
        private String state;

        /**
         * 保存状态到备忘录对象中
         * @return
         */
        public MementoImpl createMemento() {
            return new MementoImpl(state);
        }

        /**
         * 恢复备忘录中保存的状态
         * @param memento
         */
        public void restoreMemento(Memento memento) {
            setState(((MementoImpl) memento).getState());
        }

        /**
         * 备忘录内部类
         */
        @Data
        @AllArgsConstructor
        private static class MementoImpl implements Memento {
            private String state;
        }
    }

    /**
     * @description:备忘录管理者
     */
    static class Caretaker {

        private Memento memento;

        /**
         * 保存备忘录对象
         * @param memento
         */
        public void saveMemento(Memento memento) {
            this.memento = memento;
        }

        /**
         * 索取备忘录对象
         * @return
         */
        public Memento retriveMemento() {
            return this.memento;
        }
    }

}



