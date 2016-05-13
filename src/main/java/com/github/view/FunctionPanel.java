package com.github.view;

import com.github.algorithm.MyFunction;
import com.github.algorithm.SimulatedAnnealing;
import org.apache.commons.lang.ArrayUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;

public class FunctionPanel {
    public JPanel panel;
    public JSpinner spLeftLimit;
    public JSpinner spRightLimit;
    public JComboBox<ComboboxItem> cbFunction;
    public JButton btnCompute;
    public JTextField tfResult;
    private JLabel labLeftLimit;
    private JLabel labRightLimit;
    private JLabel labFunction;
    private JLabel labResult;
    private JLabel labStatus;
    private JLabel labStatusResult;
    private SimulatedAnnealing sa = new SimulatedAnnealing();

    public FunctionPanel() {
        btnCompute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (cbFunction.getSelectedIndex() > -1) {
                    ComboboxItem item = (ComboboxItem) cbFunction.getSelectedItem();

                    SwingWorker<Double[], MyFunction> worker = new SwingWorker<Double[], MyFunction>() {
                        @Override
                        protected Double[] doInBackground() throws Exception {
                            labStatusResult.setText("Obliczanie...");

                            double[] val = sa.simulatedAnnealing(
                                    item.getFunction(),
                                    n -> n * 0.999,
                                    1000,
                                    0.001,
                                    2,
                                    (Double) spLeftLimit.getValue(),
                                    (Double) spRightLimit.getValue()
                            );
                            return ArrayUtils.toObject(val);
                        }

                        @Override
                        protected void done() {
                            try {
                                double[] args = ArrayUtils.toPrimitive(get());

                                Double res = item.getFunction().f(args);

                                labStatusResult.setText(res.toString());

                                StringBuilder sb = new StringBuilder();

                                sb.append("<html>");

                                for (int i = 0; i < args.length; i++) {
                                    sb.append(String.format("x[%d] = %s<br>", i, args[i]));
                                }

                                sb.append("</html>");

                                labStatusResult.setText(sb.toString());
                                tfResult.setText(res.toString());
                            } catch (InterruptedException | ExecutionException ex) {
                                labStatusResult.setText("Błąd");
                                tfResult.setText(ex.toString());
                            }
                        }
                    };

                    worker.run();
                }
            }
        });

        cbFunction.addItemListener(e -> {
            ComboboxItem item = (ComboboxItem) cbFunction.getSelectedItem();

            spLeftLimit.setValue(item.getLeft());
            spRightLimit.setValue(item.getRight());
        });

        spLeftLimit.setModel(new SpinnerNumberModel(0, -10000, 10000, 0.01));
        spRightLimit.setModel(new SpinnerNumberModel(0, -10000, 10000, 0.01));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    public void setComboboxModel(ComboBoxModel<ComboboxItem> model) {
        cbFunction.setModel(model);
        if (cbFunction.getItemCount() > 0)
            cbFunction.setSelectedIndex(cbFunction.getItemCount() - 1);
    }
}