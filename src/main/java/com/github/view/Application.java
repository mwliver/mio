package com.github.view;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() throws HeadlessException {
        FunctionPanel panel = new FunctionPanel();
        add(panel.$$$getRootComponent$$$());
    }
}
