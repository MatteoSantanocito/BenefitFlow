import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Login extends JPanel {

    private JLabel titolo, usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JButton login;

    public Login() {
        setLayout(new GridLayout(3, 1));
        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        titolo = new JLabel("BenefitFlow");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(JLabel.LEFT);
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        formPanel.add(usernamePanel);
        formPanel.add(passwordPanel);

        login = new JButton("Login");
        buttonPanel.add(login);

        add(titlePanel);
        add(formPanel);
        add(buttonPanel);

    }
}
