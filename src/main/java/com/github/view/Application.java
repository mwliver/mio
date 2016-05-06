package com.github.view;

import com.github.algorithm.Parser;
import com.github.algorithm.ParserImpl;
import com.github.algorithm.RastriginFunction;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() throws HeadlessException {
        FunctionPanel panel = new FunctionPanel();

        Parser parser;

        parser = new ParserImpl("x0^2 + x1^2", 2);

        final DefaultComboBoxModel<ComboboxItem> defaultComboBoxModel = new DefaultComboBoxModel<>(
                new ComboboxItem[]{
                        new ComboboxItem(parser.getFunction(), parser.toString(), -100, 100),
                        new ComboboxItem(new RastriginFunction(), "Funkcja Rastrigina", -5.12, 5.12)
                }
        );

        panel.setComboboxModel(defaultComboBoxModel);

        add(panel.$$$getRootComponent$$$());
    }
}
