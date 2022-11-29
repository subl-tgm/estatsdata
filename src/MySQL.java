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
			ResultSet rsAll;
	    for(int n = 1;n<13;n++) { // i = der Monat
			ResultSet verbrauch = selectstmt.executeQuery("SELECT stromverbrauch FROM testdata WHERE date="+n);
			ResultSet strompreis = selectstmt.executeQuery("SELECT strompreis FROM testdata WHERE date="+n);
			ResultSet emission = selectstmt.executeQuery("SELECT CO2Emissionen FROM testdata WHERE date="+n);
			ResultSet stromimport = selectstmt.executeQuery("SELECT Stromimport FROM testdata WHERE date="+n);
			ResultSet stromexport = selectstmt.executeQuery("SELECT Stromexport FROM testdata WHERE date="+n);
			rsAll=selectstmt.executeQuery("SELECT * FROM testdata WHERE region='Oesterreich' AND date="+n);
			if(rsAll.next()==false) { // checkt, ob schon Werte zu Oesterreich eingetragen wurden
				String genquery= Generator.generateSum(verbrauch,strompreis,emission,stromimport,stromexport,n); // Wenn nein, dann welche generaten
				stmt.executeUpdate(genquery);
			}else {
				String genquery= Generator.generateSumNew(verbrauch,strompreis,emission,stromimport,stromexport,n); // Wenn ja, Werte durch generateNew updaten
				stmt.executeUpdate(genquery);
			}
	    }




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
