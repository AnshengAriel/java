package com.ariel.javabase.designmode.structure;

import org.junit.Test;

/**
 * 适配器模式
 */
public class AdapterTest {

    @Test
    public void testAdapter() {
        Vga vga = new USB2VGAAdapter();
        vga.projection();
    }

    interface Usb {
        void showPPT();
    }

    static class UsbImpl implements Usb {
        @Override
        public void showPPT() {
            System.out.println("PPT");
        }
    }

    interface Vga {
        void projection();
    }

    static class USB2VGAAdapter implements Vga {

        private Usb usb = new UsbImpl();

        @Override
        public void projection() {
            usb.showPPT();
        }
    }

}
