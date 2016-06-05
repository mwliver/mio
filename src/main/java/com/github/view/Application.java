package com.github.view;

import com.github.algorithm.*;

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
                        new ComboboxItem(new RastriginFunction(), "Funkcja Rastrigina", -5.12, 5.12),
                        new ComboboxItem(new HimmelblauFunction(), "Funkcja Himmelblaua", -6, 6),
                        new ComboboxItem(new BoothFunction(), "Funckcja Bootha", -10, 10),
                        new ComboboxItem(new MatyasFunction(), "Funkcja Matyasa", -10, 10),
                        new ComboboxItem(new BoothFunction(), "Funkcja Bukina", -15, 3),
                }
        );

        panel.setComboboxModel(defaultComboBoxModel);

        add(panel.getPanel());
    }
}
