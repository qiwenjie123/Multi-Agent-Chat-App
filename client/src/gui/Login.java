package gui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Login extends JPanel{
  private JPanel panelMain;
  private JTextField usernameField;
  private JLabel usernameTitle;
  private JTextField passwordField;
  private JLabel passwordTitle;
  private JButton loginButton;
  private JButton signupButton;
  private RestClient client;


  public Login(RestClient client) {
    this.client = client;
  }

  public void setSignupButton() {
    signupButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (password.length() < 6) {
          JOptionPane.showMessageDialog(signupButton, "Password must be at least six characters long");
          return;
        }
        client.signup(usernameField.getText(), new String(passwordField.getPassword()));
        JOptionPane.showMessageDialog(signupButton, client.getClientMsg());
        usernameField.setText(null);
        passwordField.setText(null);
      }
    });
  }

  public void setLoginButton() {
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        client.login(usernameField.getText(), new String(passwordField.getPassword()));

        JOptionPane.showMessageDialog(loginButton, "Connect " + client.getClientMsg());
      }
    });
  }


  public static void main(String[] args) {
    JFrame f = new JFrame();

    LoginUI p = new LoginUI(new ClientImpl());
    p.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    f.setContentPane(p.panelMain);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.setTitle("The Title");
    f.setSize(600, 400);
    f.pack();
    f.setVisible(true);

  }
}
