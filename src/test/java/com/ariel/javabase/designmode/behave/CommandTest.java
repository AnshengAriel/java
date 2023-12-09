package com.ariel.javabase.designmode.behave;

import org.junit.Test;

/**
 * 命令模式
 */
public class CommandTest {

    @Test
    public void testCommand() {
        Receiver receiver = new Receiver();
        ConcreteCommand concreteCommand = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();

        invoker.setCommand(concreteCommand);
        invoker.invoke();
    }


    interface Command {
        void execute();
    }

    /**
     * 具体的命令实现
     */
    static class ConcreteCommand implements Command {

        /**
         * 持有相应的接收者对象
         */
        private Receiver receiver = null;

        /**
         * 构造方法，传入相应的接收者对象
         *
         * @param receiver 相应的接收者对象
         */
        public ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        /**
         * 执行命令
         */
        @Override
        public void execute() {
            // 通常会转调接收者对象的相应方法，让接收者来真正执行功能
            receiver.action();
        }

    }

    /**
     * 命令的接收者
     */
    static class Receiver {

        /**
         * 示意方法，真正执行命令相应的操作
         */
        public void action() {
            System.out.println("接收者开始行动。。。");
        }
    }

    /**
     * 命令的调用者
     */
    static class Invoker {

        /**
         * 持有命令对象
         */
        private Command command = null;

        /**
         * 设置调用者持有的命令对象
         *
         * @param command 命令对象
         */
        public void setCommand(Command command) {
            this.command = command;
        }

        /**
         * 示意方法，调用命令执行请求
         */
        public void invoke() {
            command.execute();
        }
    }
}
