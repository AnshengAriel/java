/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package javafx.scene.control;

/**
Builder class for javafx.scene.control.Tooltip
@see javafx.scene.control.Tooltip
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public class TooltipBuilder<B extends javafx.scene.control.TooltipBuilder<B>> extends javafx.scene.control.PopupControlBuilder<B> {
    protected TooltipBuilder() {
    }

    /** Creates a new instance of TooltipBuilder. */
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public static javafx.scene.control.TooltipBuilder<?> create() {
        return new javafx.scene.control.TooltipBuilder();
    }

    private int __set;
    private void __set(int i) {
        __set |= 1 << i;
    }
    public void applyTo(javafx.scene.control.Tooltip x) {
        super.applyTo(x);
        int set = __set;
        while (set != 0) {
            int i = Integer.numberOfTrailingZeros(set);
            set &= ~(1 << i);
            switch (i) {
                case 0: x.setContentDisplay(this.contentDisplay); break;
                case 1: x.setFont(this.font); break;
                case 2: x.setGraphic(this.graphic); break;
                case 3: x.setGraphicTextGap(this.graphicTextGap); break;
                case 4: x.setText(this.text); break;
                case 5: x.setTextAlignment(this.textAlignment); break;
                case 6: x.setTextOverrun(this.textOverrun); break;
                case 7: x.setWrapText(this.wrapText); break;
            }
        }
    }

    private javafx.scene.control.ContentDisplay contentDisplay;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getContentDisplay() contentDisplay} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B contentDisplay(javafx.scene.control.ContentDisplay x) {
        this.contentDisplay = x;
        __set(0);
        return (B) this;
    }

    private javafx.scene.text.Font font;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getFont() font} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B font(javafx.scene.text.Font x) {
        this.font = x;
        __set(1);
        return (B) this;
    }

    private javafx.scene.Node graphic;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getGraphic() graphic} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B graphic(javafx.scene.Node x) {
        this.graphic = x;
        __set(2);
        return (B) this;
    }

    private double graphicTextGap;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getGraphicTextGap() graphicTextGap} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B graphicTextGap(double x) {
        this.graphicTextGap = x;
        __set(3);
        return (B) this;
    }

    private java.lang.String text;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getText() text} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B text(java.lang.String x) {
        this.text = x;
        __set(4);
        return (B) this;
    }

    private javafx.scene.text.TextAlignment textAlignment;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getTextAlignment() textAlignment} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B textAlignment(javafx.scene.text.TextAlignment x) {
        this.textAlignment = x;
        __set(5);
        return (B) this;
    }

    private javafx.scene.control.OverrunStyle textOverrun;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#getTextOverrun() textOverrun} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B textOverrun(javafx.scene.control.OverrunStyle x) {
        this.textOverrun = x;
        __set(6);
        return (B) this;
    }

    private boolean wrapText;
    /**
    Set the value of the {@link javafx.scene.control.Tooltip#isWrapText() wrapText} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B wrapText(boolean x) {
        this.wrapText = x;
        __set(7);
        return (B) this;
    }

    /**
    Make an instance of {@link javafx.scene.control.Tooltip} based on the properties set on this builder.
    */
    public javafx.scene.control.Tooltip build() {
        javafx.scene.control.Tooltip x = new javafx.scene.control.Tooltip();
        applyTo(x);
        return x;
    }
}