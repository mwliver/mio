package com.github.view;

import com.github.algorithm.MyFunction;
import com.github.algorithm.SimulatedAnnealing;
import org.apache.commons.lang.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
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
                                    sb.append(String.format("x[%d] = %f<br>", i, args[i]));
                                }

                                sb.append("</html>");

                                labStatusResult.setText(sb.toString());
                                tfResult.setText(String.format("%f", res.doubleValue()));

                                XYSeries bestsSeries = new XYSeries("Najlepszy");
                                Queue<double[]> bests = sa.getBests();

                                for (int i = 0; i < bests.size(); i++) {
                                    bestsSeries.add(i, item.getFunction().f(bests.poll()));
                                }

                                XYSeries currnentsSeries = new XYSeries("Obecny");
                                Queue<double[]> currents = sa.getCurrents();

                                for (int j = 0; j < currents.size(); j++) {
                                    currnentsSeries.add(j, item.getFunction().f(currents.poll()));
                                }

                                XYSeriesCollection dataset = new XYSeriesCollection();
                                dataset.addSeries(bestsSeries);
//                                dataset.addSeries(currnentsSeries);

                                JFreeChart chart = ChartFactory.createXYLineChart(
                                        "Wykres zbieżności",
                                        "iteracja",
                                        "wartość",
                                        dataset,
                                        PlotOrientation.VERTICAL,
                                        true,
                                        true,
                                        false
                                );

                                ChartFrame chartFrame = new ChartFrame("Wykres zbieżności", chart, true);
                                chartFrame.setExtendedState(chartFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                                chartFrame.setVisible(true);

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

    public JComponent getPanel() {
        return panel;
    }

    public void setComboboxModel(ComboBoxModel<ComboboxItem> model) {
        cbFunction.setModel(model);
        if (cbFunction.getItemCount() > 0)
            cbFunction.setSelectedIndex(cbFunction.getItemCount() - 1);
    }
}