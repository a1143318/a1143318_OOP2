
import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;

    public LoginWindow() {
        // 設定視窗
        setTitle("登入系統");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 建立主要面板
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // 建立標籤
        JLabel accountLabel = new JLabel("帳號：");
        JLabel passwordLabel = new JLabel("密碼：");

        // 建立輸入框
        accountField = new JTextField();
        passwordField = new JPasswordField();

        // 建立按鈕
        loginButton = new JButton("登入");
        clearButton = new JButton("清除");

        // 將元件加入面板
        panel.add(accountLabel);
        panel.add(accountField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(loginButton);
        panel.add(clearButton);

        // 加入視窗
        add(panel);

        // 登入按鈕事件
        loginButton.addActionListener(e -> {
            String account = accountField.getText();
            String password =
                new String(passwordField.getPassword());

            if (account.equals("admin")
                    && password.equals("1234")) {
                JOptionPane.showMessageDialog(
                    this, "登入成功！"
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "帳號或密碼錯誤！",
                    "登入失敗",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // 清除按鈕事件
        clearButton.addActionListener(e -> {
            accountField.setText("");
            passwordField.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow window = new LoginWindow();
            window.setVisible(true);
        });
    }
}
