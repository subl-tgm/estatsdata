import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class MySQLFile {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * @param args
     * @throws IOException
     * Connection zur Datenbank aufbauen und Generator aufrufen, um Daten zu generieren und in die Datenbank einzufuegen
     */
    public static void main(String[] args) throws IOException {
        Date date;
        Timestamp timestamp;

        try{
            // Filepfad anwählen
            File myFile = new File("estatsdata\\output\\output.sql");
            // Falls die Datei noch nicht existiert, wird sie erstellt
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // Timestamp erstellen
        while (true) {
            date = new Date();
            timestamp = new Timestamp(date.getTime());
            System.out.println(sdf.format(timestamp));
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Variablen deklarieren und Generator aufrufen
            String state="";
            double stromverbrauch=0;
            int strompreis=0;
            double co2Emissionen=0;
            double stromimport=0;
            double stromexport=0;
            int count=0;
            // Diese Schleife geht durch alle Bundesländer und speichert die generierten Daten in der Datenbank
            for (int j = 0; j < 9; j++) {
                // data ist ein Array mit den Daten die der Generator generiert
                Double[] data = Generatornew.genData(j);
                // da preis ein int ist müssen wir diesen wieder aus dem Double Array umwandeln
                int preis = data[1].intValue();

                // Bundesland herausfinden
                switch(data[5].intValue()) {
                    case 0:
                        state="Burgenland";
                        break;
                    case 1:
                        state="Kaernten";
                        break;
                    case 2:
                        state="Niederoesterreich";
                        break;
                    case 3:
                        state="Oberoesterreich";
                        break;
                    case 4:
                        state="Salzburg";
                        break;
                    case 5:
                        state="Steiermark";
                        break;
                    case 6:
                        state="Tirol";
                        break;
                    case 7:
                        state="Vorarlberg";
                        break;
                    case 8:
                        state="Wien";
                        break;
                }
                // SQL Query für jedes Bundesland erstellen
                String genquery = "INSERT INTO estats " + "VALUES ('" + state + "',0.0," + data[0] + "," + preis + "," + data[2] + "," + data[3] + "," + data[4] + ",'"+sdf.format(timestamp)+"');";
                try {
                    // Die Daten werden dem output.sql File hinzugefügt, durch den speziellen Konstruktor (append:true) wird der Inhalt nicht überschrieben, sondern ergänzt
                    BufferedWriter fileWriter= new BufferedWriter(new FileWriter("estatsdata\\output\\output.sql", true));
                    fileWriter.write(genquery + "\n");
                    fileWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                stromverbrauch+=data[0];
                strompreis+=preis;
                co2Emissionen+=data[2];
                stromimport+=data[3];
                stromexport+=data[4];
            }
            // Durchschnittspreis für Österreich berechnen
            strompreis=strompreis/9;
            // SQL Query für ganz Österreich erstellen
            String queryAll = "INSERT INTO estats " + "VALUES ('" + "Oesterreich" + "',100.0," + stromverbrauch + "," + strompreis + "," + co2Emissionen + "," + stromimport + "," + stromexport + ",'"+ sdf.format(timestamp)+"')";
            try {
                // Die Daten werden dem output.sql File hinzugefügt, durch den speziellen Konstruktor (append:true) wird der Inhalt nicht überschrieben, sondern ergänzt
                BufferedWriter fileWriter= new BufferedWriter(new FileWriter("estatsdata\\output\\output.sql", true));
                fileWriter.write(queryAll + "\n");
                fileWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println("Record is inserted in the table successfully..................");
            System.out.println("Please check it in the MySQL Table..........");
        }
    }
}

