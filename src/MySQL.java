import java.sql.*;

public class MySQL {

	public static void main(String[] args) {
		
		// Connection zu MySQL aufbauen
		Connection conn = null;
		Statement stmt = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
        	System.out.println(e);
        }
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
	    System.out.println("Connection is created successfully:");

	    //Statement machen und Generator aufrufen
	    stmt = (Statement) conn.createStatement();
	    Statement selectstmt = conn.createStatement();
	    ResultSet rs1;
	    for(int i = 1;i<13;i++) { // i = der Monat
	    	for(int j=0;j<9;j++) { // j = das Bundesland
	    		String reg="";
	    		switch(j) {
	    		case(0):
	    			reg="Burgenland";
	    			break;
	    		case(1):
	    			reg="Kaernten";
	    			break;
	    		case(2):
	    			reg="Niederoesterreich";
	    			break;
	    		case(3):
	    			reg="Oberoesterreich";
	    			break;
	    		case(4):
	    			reg="Salzburg";
	    			break;
	    		case(5):
	    			reg="Steiermark";
	    			break;
	    		case(6):
	    			reg="Tirol";
	    			break;
	    		case(7):
	    			reg="Vorarlberg";
	    			break;
	    		case(8):
	    			reg="Wien";
	    			break;
	    		}
	    		rs1=selectstmt.executeQuery("SELECT * FROM testdata WHERE region='"+reg+"' AND date="+i);
	    		if(rs1.next()==false) { // checkt, ob das Bundesland in dem Monat schon Daten hatte
	    			String genquery= Generator.generate(j,i); // Wenn nein, dann welche generaten
		            stmt.executeUpdate(genquery);
	    		}else {
	    			String genquery= Generator.generateNew(j,i); // Wenn ja, Werte durch generateNew updaten
		            stmt.executeUpdate(genquery);
	    		}
	    	}
	    }
	    System.out.println("Record is inserted in the table successfully..................");

	    // Gesamtstatistiken für ganz Österreich machen
	    // jedes Select braucht ein eigenes Statement da die ResultSets sich sonst schließen und man keine Werte mehr zurückbekommt
			ResultSet verbrauch;
			Statement preisstmt = conn.createStatement();
			ResultSet strompreis;
			Statement emissionstmt = conn.createStatement();
			ResultSet emission;
			Statement importstmt = conn.createStatement();
			ResultSet stromimport;
			Statement exportstmt = conn.createStatement();
			ResultSet stromexport;

			// für jeden Monat werden alle Werte aus der Datenbank geholt und in Variablen gespeichert
			// dann werden die Werte an die generateSum oder generateSumNew Funktion übergeben und die Querys generiert
	    for(int n = 1;n<13;n++) { // i = der Monat
			Statement verbrauchstmt = conn.createStatement();
			verbrauch = verbrauchstmt.executeQuery("SELECT stromverbrauch FROM testdata WHERE region <> 'Oesterreich' AND date="+n);
			strompreis = preisstmt.executeQuery("SELECT strompreis FROM testdata WHERE region <> 'Oesterreich' AND date="+n);
			emission = emissionstmt.executeQuery("SELECT CO2Emissionen FROM testdata WHERE region <> 'Oesterreich' AND date="+n);
			stromimport = importstmt.executeQuery("SELECT Stromimport FROM testdata WHERE region <> 'Oesterreich' AND date="+n);
			stromexport = exportstmt.executeQuery("SELECT Stromexport FROM testdata WHERE region <> 'Oesterreich' AND date="+n);
			ResultSet rsAll=selectstmt.executeQuery("SELECT * FROM `testdata` WHERE `region`=\"Oesterreich\" AND date="+n);
			if(rsAll.next()==false) { // checkt, ob schon Werte zu Oesterreich eingetragen wurden
				String genquery= Generator.generateSum(verbrauch,strompreis,emission,stromimport,stromexport,n); // Wenn nein, dann welche generaten
				stmt.executeUpdate(genquery);
			}else {
				String genquery= Generator.generateSumNew(verbrauch,strompreis,emission,stromimport,stromexport,n); // Wenn ja, Werte durch generateSumNew updaten
				stmt.executeUpdate(genquery);
			}
	    }
		System.out.println("Record is inserted in the table successfully..................");




		} catch (SQLException excep) {
			excep.printStackTrace();
		} catch (Exception excep) {
			excep.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
         } catch (SQLException se) {}
         try {
        	 if (conn != null)
        		 conn.close();
         } catch (SQLException se) {
        	 se.printStackTrace();
         }  
      }
      System.out.println("Please check it in the MySQL Table..........");
   }
}
