package stuff;

import Datenbank.Persistenz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginUI {

    Persistenz p;

    public LoginUI() {

        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        JPanel main = new JPanel(new GridLayout(6, 1));

        JTextField username = new JTextField();
        JTextField password = new JTextField();

        JLabel usernameL = new JLabel("username");
        JLabel passwordL = new JLabel("password");

        JButton login = new JButton("Submit");
        JButton register = new JButton("Register");

        main.add(usernameL);
        main.add(username);
        main.add(passwordL);
        main.add(password);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> reading = Persistenz.reading();
                for (User user : reading) {
                    if (user.getUserName().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                        Zentrale.getInstance().setActiveUser(user);
                        break;
                    }
                }

                System.out.println("Login war erfolgreich? " + Zentrale.getInstance().getActiveUser() != null);

            }
        });


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                JFrame frame_r = new JFrame();
                frame_r.setSize(400, 500);
                JPanel main_r = new JPanel(new GridLayout(7, 1));


                JTextField username_r = new JTextField();
                JTextField alter_r = new JTextField();
                JTextField password_r = new JTextField();


                JLabel usernameL_r = new JLabel("username");
                JLabel alterL_r = new JLabel("alter");
                JLabel passwordL_r = new JLabel("password");

                JButton submit = new JButton("Submit");

                main_r.add(usernameL_r);
                main_r.add(username_r);
                main_r.add(alterL_r);
                main_r.add(alter_r);
                main_r.add(passwordL_r);
                main_r.add(password_r);


                ActionListener submitA = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<User> reading = Persistenz.reading();
                        User register_user = new User(username_r.getText(), alter_r.getText(), password_r.getText());
                        reading.add(register_user);
                        p.writing(reading);

                        frame_r.setVisible(false);
                        frame.setVisible(true);

                    }
                };

                submit.addActionListener(submitA);
                main_r.add(submit);

                frame_r.add(main_r);
                frame_r.setVisible(true);
            }
        });
        main.add(login);
        main.add(register);
        frame.add(main);
        frame.setVisible(true);
    }
}

