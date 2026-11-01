
import javax.swing.*;
import java.awt.*;

public class UnitConverter extends JFrame {

    private JComboBox<String> typeBox;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;

    private JTextField inputField;
    private JTextField resultField;

    private JButton convertButton;

    // 長度、重量、溫度的單位
    private String[][] units = {
        {"公尺", "公分", "英吋", "英尺"},
        {"公斤", "公克", "磅", "盎司"},
        {"攝氏", "華氏", "克氏"}
    };

    public UnitConverter() {

        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // NORTH：換算類型
        typeBox = new JComboBox<>(
            new String[]{"長度", "重量", "溫度"}
        );

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("換算類型："));
        northPanel.add(typeBox);

        add(northPanel, BorderLayout.NORTH);

        // CENTER：兩列輸入區
        inputField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        fromBox = new JComboBox<>(units[0]);
        toBox = new JComboBox<>(units[0]);

        JPanel centerPanel = new JPanel(
            new GridLayout(2, 1, 5, 10)
        );

        JPanel row1 = new JPanel();
        row1.add(new JLabel("來源數值："));
        row1.add(inputField);
        row1.add(fromBox);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("換算結果："));
        row2.add(resultField);
        row2.add(toBox);

        centerPanel.add(row1);
        centerPanel.add(row2);

        add(centerPanel, BorderLayout.CENTER);

        // SOUTH：換算按鈕
        convertButton = new JButton("換算");

        JPanel southPanel = new JPanel();
        southPanel.add(convertButton);

        add(southPanel, BorderLayout.SOUTH);

        // 切換類型時，更新兩個單位選單
        typeBox.addActionListener(e -> {
            int index = typeBox.getSelectedIndex();

            fromBox.setModel(
                new DefaultComboBoxModel<>(units[index])
            );

            toBox.setModel(
                new DefaultComboBoxModel<>(units[index])
            );

            resultField.setText("");
        });

        // 按下換算按鈕
        convertButton.addActionListener(e -> convert());

        setVisible(true);
    }

    // 執行換算
    private void convert() {

        try {
            double value = Double.parseDouble(
                inputField.getText().trim()
            );

            int type = typeBox.getSelectedIndex();
            int from = fromBox.getSelectedIndex();
            int to = toBox.getSelectedIndex();

            double result;

            if (type == 0) {
                // 長度：先換成公尺，再換成目標單位
                double[] factor = {
                    1.0, 0.01, 0.0254, 0.3048
                };

                result = value * factor[from] / factor[to];

            } else if (type == 1) {
                // 重量：先換成公斤，再換成目標單位
                double[] factor = {
                    1.0, 0.001, 0.45359237, 0.028349523125
                };

                result = value * factor[from] / factor[to];

            } else {
                // 溫度：先換成攝氏，再換成目標單位
                double celsius;

                if (from == 0) {
                    celsius = value;
                } else if (from == 1) {
                    celsius = (value - 32) * 5.0 / 9.0;
                } else {
                    celsius = value - 273.15;
                }

                if (to == 0) {
                    result = celsius;
                } else if (to == 1) {
                    result = celsius * 9.0 / 5.0 + 32;
                } else {
                    result = celsius + 273.15;
                }
            }

            resultField.setText(
                String.format("%.4f", result)
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "請輸入有效的數字！",
                "輸入錯誤",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UnitConverter();
        });
    }
}
