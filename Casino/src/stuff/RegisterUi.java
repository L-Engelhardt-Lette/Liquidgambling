package stuff;

import Datenbank.Persistenz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterUi {

    Persistenz p;
    User u;

    public RegisterUi(){
        JFrame frame = new JFrame();
        frame.setSize(400,500);
        JPanel main = new JPanel(new GridLayout(9,1 ));


        JTextField username = new JTextField();
        JTextField alter = new JTextField();
        JTextField password = new JTextField();


        JLabel usernameL = new JLabel("username");
        JLabel alterL = new JLabel("alter");
        JLabel passwordL = new JLabel("password");

        JButton submit = new JButton("Submit");

        main.add(usernameL);
        main.add(username);
        main.add(alterL);
        main.add(alter);
        main.add(passwordL);
        main.add(password);


        ActionListener submitA = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> reading = Persistenz.reading();
                User active_user = new User(username.getText(), alter.getText(), password.getText());
                reading.add(active_user);
                p.writing(reading);

                frame.setVisible(false);

            }
        };
        submit.addActionListener(submitA);
        main.add(submit);

        frame.add(main);
        frame.setVisible(true);
    }

}
