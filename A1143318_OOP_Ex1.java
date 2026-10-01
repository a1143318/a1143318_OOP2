import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class A1143318_OOP2_EX1 extends JFrame {

    private JLabel diceLabel;
    private JLabel infoLabel;

    private int count = 0;
    private int sum = 0;

    public A1143318_OOP2_EX1() {

        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        infoLabel = new JLabel("已擲 0 次，總和 0，平均 0.00");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);

        diceLabel = new JLabel("—");
        diceLabel.setHorizontalAlignment(JLabel.CENTER);
        diceLabel.setFont(new Font("Arial", Font.PLAIN, 60));

        JButton rollButton = new JButton("擲骰子");

        rollButton.addActionListener(e -> rollDice());

        setLayout(new BorderLayout());

        add(infoLabel, BorderLayout.NORTH);
        add(diceLabel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void rollDice() {

        Random random = new Random();

        int dice = random.nextInt(6) + 1;

        count++;
        sum += dice;

        diceLabel.setText(String.valueOf(dice));

        if (dice == 6) {
            diceLabel.setForeground(Color.GREEN);
        } else if (dice == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }

        double average = (double) sum / count;

        infoLabel.setText(
            String.format("已擲 %d 次，總和 %d，平均 %.2f",
                    count, sum, average)
        );
    }

    public static void main(String[] args) {
        new A1143318_OOP2_EX1();
    }
}