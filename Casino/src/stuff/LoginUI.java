package stuff;

import Datenbank.Persistenz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginUI {

    Persistenz p;
    User user_login;
    RegisterUi register;

 public LoginUI(){

     JFrame frame = new JFrame();
     frame.setSize(400,500);
     JPanel main = new JPanel(new GridLayout(9,1 ));

     JTextField username = new JTextField();
     JTextField alter = new JTextField();
     JTextField password = new JTextField();

     JLabel usernameL = new JLabel("username");
     JLabel alterL = new JLabel("alter");
     JLabel passwordL = new JLabel("password");

     JButton login = new JButton("Submit");
     JButton register = new JButton("Register");

     main.add(usernameL);
     main.add(username);
     main.add(alterL);
     main.add(alter);
     main.add(passwordL);
     main.add(password);

     login.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             ArrayList<User> reading = Persistenz.reading();





         }

     });

 }
}
