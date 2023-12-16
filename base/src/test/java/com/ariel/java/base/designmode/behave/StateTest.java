package com.ariel.java.base.designmode.behave;

import lombok.Data;
import org.junit.Test;

/**
 * 状态模式
 */
public class StateTest {

    @Test
    public void testPreSingleTon() {
        new Context(new ConcreteStateA()).handle();
    }


    @Data
    static class Context {
        private State state;

        public Context(State state) {
            this.state = state;
        }

        public void handle() {
            state.handle(this);
        }
    }

    interface State {
        void handle(Context context);
    }

    static class ConcreteStateA implements State {
        public void handle(Context context) {
            System.out.println("当前状态是 A.");
            context.setState(new ConcreteStateB());
        }
    }

    static class ConcreteStateB implements State {
        public void handle(Context context) {
            System.out.println("当前状态是 B.");
            context.setState(new ConcreteStateA());
        }
    }
}


