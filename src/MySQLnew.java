import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySQLnew {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**
         * @param args
         * @throws SQLException
         * Connection zur Datenbank aufbauen und Generator aufrufen um Daten zu generieren und in die Datenbank einzufuegen
         */
        public static void main(String[] args) throws SQLException {
            Date date;
            Timestamp timestamp;
            // Connection zu MySQL aufbauen
            Connection conn = null;
            Statement stmt = null;
            try {
                try {
                    Class.forName("com.mysql.jc.jdbc.Driver");
                } catch (Exception e) {
                    System.out.println(e);
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
                System.out.println("Connection is created successfully:");
                while (true) {
                    date = new Date();
                    timestamp = new Timestamp(date.getTime());
                    System.out.println(sdf.format(timestamp) + " oder " + timestamp);
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Statement machen und Generator aufrufen
                    stmt = conn.createStatement();
                     String state="";
                    double stromverbrauch=0;
                    int strompreis=0;
                    double co2Emissionen=0;
                    double stromimport=0;
                    double stromexport=0;
                    int count=0;
                    for (int j = 0; j < 9; j++) { // cycle durch alle bundesländer
                        Double[] data = Generatornew.genData(j);
                        int preis = data[1].intValue();

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

                        String genquery = "INSERT INTO estats " + "VALUES ('" + state + "',0.0," + data[0] + "," + preis + "," + data[2] + "," + data[3] + "," + data[4] + ",'"+sdf.format(timestamp)+"');";
                        stmt.executeUpdate(genquery);
                        System.out.println("Query für: " + state + " erfolgreich");
                        stromverbrauch+=data[0];
                        strompreis+=preis;
                        co2Emissionen+=data[2];
                        stromimport+=data[3];
                        stromexport+=data[4];
                    }
                    strompreis=strompreis/9;
                    String queryAll = "INSERT INTO estats " + "VALUES ('" + "Oesterreich" + "',100.0," + stromverbrauch + "," + strompreis + "," + co2Emissionen + "," + stromimport + "," + stromexport + ",'"+ sdf.format(timestamp)+"')";
                    stmt.executeUpdate(queryAll);
                    // Gesamtstatistiken für ganz Österreich machen
                    System.out.println("Record is inserted in the table successfully..................");
                    System.out.println("Please check it in the MySQL Table..........");
                }
            }catch (SQLException e) {
                System.out.println(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

