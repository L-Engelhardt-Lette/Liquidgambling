package Backend.Datenbank;

import Backend.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/* Diese Class schreibt und liest die Datenbank.txt um User updates zu machen und neue aufzuschreiben. Erstellt auch ein Backup */

public class Persistenz {

    public static void writing(Collection<User> alleUser) {
        BufferedWriter writer; //schreiber für die Datenbank.txt
        BufferedWriter writer_backup; //schreiber für die Backup.txt
        try {
            writer = new BufferedWriter(new FileWriter("Casino/src/Backend/Datenbank/Databank.txt")); //schreiben in datenbank.txt festlegen
            writer_backup = new BufferedWriter(new FileWriter("Casino/src/Backend/Datenbank/Backup.txt")); // scheiben in backup.txt festlegen

            // alle User schreiben in die Datenbank.txt und überschreibt weshalb wir eine Backup.txt erstellen damit nichts verloren geht
            for (User user : alleUser) {
                String temp = user.getUserName()+";"+ user.getAge()+";"+user.getPassword()+";"+user.getUser_Pearl()+";"+user.getFreeSpin()+";";
                writer.write(temp);
                writer.newLine();
                writer_backup.write(temp);
                writer_backup.newLine();
            }
            writer.close();
            writer_backup.close();
        }
        catch (FileNotFoundException fileNotFoundException) {System.out.println(102);}
        catch (IOException io){System.out.println(103);}
    }
    public static ArrayList<User> reading() {
        ArrayList<User> ergebnis = new ArrayList<>();
        BufferedReader reader; //leser für die Datenbank.txt
        try {
            reader = new BufferedReader(new FileReader("Casino/src/Backend/Datenbank/Databank.txt")); // leser in Datenbank.txt festlegen
            String line = reader.readLine();
            // liest die Datenbank.txt aus und gibt die user einzeln aus in einem Array gesplittet damit die 3 Daten übergeben werden können
            while (line != null){
                String[] split = line.split(";");
                ergebnis.add(new User(split[0],split[1],split[2], Integer.parseInt(split[3])));
                line = reader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {System.out.println(102);}
          catch (IOException e) {
            e.printStackTrace();
        }
        // die Arraylist mit allen Usern wird zurückgegeben
        return ergebnis;
    }
}
//CHRIS IST BAD MIT GITHUB