package Datenbank;

import stuff.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Persistenz {



    public static void writing(Collection<User> alleUser) {

        BufferedWriter writer;
        BufferedWriter writer_backup;

        try {
            writer = new BufferedWriter(new FileWriter("src/Datenbank/Databank.txt"));
            writer_backup = new BufferedWriter(new FileWriter("src/Datenbank/Backup.txt"));

            for (User user : alleUser) {
                String temp = user.getUserName()+";"+ user.getAge()+";"+user.getPassword()+";"+user.getUser_Pearl()+";"+user.getFreeSpin()+";";
                writer.write(temp);
                writer.newLine();
                writer_backup.write(temp);
                writer_backup.newLine();
            }

            writer.close();
            writer_backup.close();
        } catch (FileNotFoundException fileNotFoundException) {System.out.println(102);
        }
        catch (IOException io){System.out.println(103);
        }



    }

    public static ArrayList<User> reading() {


        ArrayList<User> ergebnis = new ArrayList<>();

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/Datenbank/Databank.txt"));
            String line = reader.readLine();
            while (line != null){

                String[] split = line.split(";");
                ergebnis.add(new User(split[0], split[1], split[2]));
                line = reader.readLine();

            }

        } catch (FileNotFoundException fileNotFoundException) {System.out.println(102);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ergebnis;

    }

}
