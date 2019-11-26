package Login;
import  javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame implements ActionListener{

    Container container = getContentPane();
    JLabel userLabel  = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField usertextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    public Login(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public  void  setLayoutManager(){
        container.setLayout(null);
    }
    public  void  setLocationAndSize(){
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,220,100,30);
        usertextField.setBounds(150,150,150,30);
        passwordField.setBounds(150,220,150,30);
        showPassword.setBounds(150,250,150,30);
        loginButton.setBounds(50,330,100,30);
        resetButton.setBounds(200,330,100,30);
    }
    public  void addComponentsToContainer(){
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(usertextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }
    public  void  addActionEvent(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
    @Override
    public  void  actionPerformed(ActionEvent e){

    }


}
