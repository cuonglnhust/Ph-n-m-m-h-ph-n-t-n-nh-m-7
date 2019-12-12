package Login;

import main.Handler;
import rmi.client.ClientLogin;
import SCCommon.ConnectionData;
import state.HomeState;
import state.StartState;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class Login extends JFrame {

    Container container = getContentPane();
    //    JLabel LoginLabel = new JLabel("ĐĂNG NHẬP");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel IPAddressLabel = new JLabel("IPADDRESS");
    JTextField usertextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField IPAddressField = new JTextField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    private StartState startState;


    public void CheckLogin(String Username, char[] Password) {

        String name = usertextField.getText();
        char[] pass = passwordField.getPassword();
        if (Username == name && Password == pass) {

        }
    }

    public Login(StartState startState) throws SQLException {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.startState = startState;
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
//        LoginLabel.setBounds(20,200,150,40);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        IPAddressLabel.setBounds(50, 290, 100, 30);
        IPAddressField.setBounds(150, 290, 150, 30);
        usertextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 350, 150, 30);
        loginButton.setBounds(50, 390, 100, 30);
        resetButton.setBounds(200, 390, 100, 30);
    }

    public void addComponentsToContainer() {
//        container.add(LoginLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(usertextField);
        container.add(passwordField);
        container.add(IPAddressLabel);
        container.add(IPAddressField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }


    public void addActionEvent() throws SQLException {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (usertextField.getText().equals("") || String.copyValueOf(passwordField.getPassword()).equals("") || IPAddressField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tài khoản, mật khẩu, IP không được bỏ trống");
                } else {
                    ClientLogin clientLogin = new ClientLogin(new ConnectionData(IPAddressField.getText().trim(), 9999, "abc"));
                    Handler.getInstance().setClientLogin(clientLogin);
                    try {
                        if (Handler.getInstance().getClientLogin().connection(usertextField.getText().trim(), String.copyValueOf(passwordField.getPassword()).trim())) {
                            State.setCurrentState(new HomeState());
                            startState.getLogin().setVisible(false);
                            startState.getLogin().dispose();

                        } else {
                            JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }


        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                usertextField.setText("");
                passwordField.setText("");
                IPAddressField.setText("");
            }
        });
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                passwordField.setEchoChar((char) 0);
            }
        });
//
    }

    public String getIp() {
        return IPAddressField.getText();
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        usertextField.setText("Hello");
//    }


}
