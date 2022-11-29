import java.sql.*;

public class Generator {

	public static String generate(int n,int date) {
		if(date<1||date>12) {
			System.out.println("Datum muss eine Zahl zwischen 1 und 12 sein entsprechend dem Monat des Jahres.");
			return null;
		}
		// int n wird der generate methode sagen was sie generieren soll
		// Alle Kategorien brauchen zufaellig erzeugte Testdaten
		double multiplier=1.0;
		String state="";
		
		// Jedes Bundesland bekommt einen Faktor mit dem die Daten multipliziert werden um akurate testdaten zu generieren
		switch(n) {
		case 0:
			multiplier = 1.0;	//burgenland
			state="Burgenland";
			break;
		case 1:
			multiplier = 2.4; 	//kaernten
			state="Kaernten";
			break;
		case 2:
			multiplier = 5.0;	//niederoesterreich
			state="Niederoesterreich";
			break;
		case 3:
			multiplier = 6.2;	//oberoesterreich
			state="Oberoesterreich";
			break;
		case 4:
			multiplier = 1.6;	//salzburg
			state="Salzburg";
			break;
		case 5:
			multiplier = 4.4;	//steiermark
			state="Steiermark";
			break;
		case 6:
			multiplier = 2.1;	//tirol
			state="Tirol";
			break;
		case 7:
			multiplier = 1.3;	//vorarlberg
			state="Vorarlberg";
			break;
		case 8:
			multiplier = 4.2;	//wien
			state="Wien";
			break;
		}
		
		
		// stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport
		
		// stromverbrauch:
		int minstromverbrauch = 300000;
		int maxstromverbrauch = 500000;
		double stromverbrauch = Math.floor(Math.random()*(maxstromverbrauch-minstromverbrauch+1)+minstromverbrauch)/10000 * multiplier;	
		stromverbrauch =((double)((int)(stromverbrauch *10000.0)))/10000.0; //Cuttet �berfl�ssige Dezimalstellen
		System.out.println("Stromverbrauch von "+ state + ": "+stromverbrauch+"PJ");
		
		// strompreis:
		int minstrompreis = 12;
		int maxstrompreis = 20;
		int strompreis = (int) Math.floor(Math.random()*(maxstrompreis-minstrompreis+1)+minstrompreis);	
		System.out.println("Strompreis von "+ state + ": "+strompreis+"c/kWh");
		
		//CO2-Emissionen
		int minemission = 50000;
		int maxemission = 100000;
		double emission = Math.floor(Math.random()*(maxemission-minemission+1)+minemission)/10000 * multiplier*1.1;	
		emission =((double)((int)(emission *10000.0)))/10000.0;
		System.out.println("CO2-Emissionen von "+ state + ": "+emission+"g/kWh");
		
		// Stromimport
		int minstromimport = 29000;
		int maxstromimport = 60000;
		double stromimport = Math.floor(Math.random()*(maxstromimport-minstromimport+1)+minstromimport)/10000 * multiplier;	
		stromimport =((double)((int)(stromimport *10000.0)))/10000.0;
		System.out.println("Stromimport von "+ state + ": "+stromimport+"GWh");
		
		// Stromexport
		int minstromexport = 20000;
		int maxstromexport = 30000;
		double stromexport = Math.floor(Math.random()*(maxstromexport-minstromexport+1)+minstromexport)/10000 * (multiplier*0.9);	
		stromexport =((double)((int)(stromexport *10000.0)))/10000.0;
		System.out.println("Stromexport von "+ state + ": "+stromexport+"GWh");
		
		String result="INSERT INTO testdata " + "VALUES ('"+state+"',0.0,"+stromverbrauch+","+strompreis+","+emission+","+stromimport+","+stromexport+","+date+")";
		System.out.println(result);
		return result;
	}
	
	/**
	 * Diese Methode wird verwendet wenn schon Datens�tze mit der gewollten Region und Datum existiert um die Werte upzudaten.
	 * @param n steht f�r eines der 9 Bundesl�nder
	 * @param date eine zahl zwischen 1 und 12, f�r die 12 Monate eines Jahres
	 * @return result String welcher die Update Query zur�ckgibt
	 */
	public static String generateNew(int n,int date) {
		if(date<1||date>12) {
			System.out.println("Datum muss eine Zahl zwischen 1 und 12 sein entsprechend dem Monat des Jahres.");
			return null;
		}
		// int n wird der generate methode sagen, was sie generieren soll
		// Alle Kategorien brauchen zufaellig erzeugte Testdaten
		double multiplier=1.0;
		String state="";
		
		// Jedes Bundesland bekommt einen Faktor mit dem die Daten multipliziert werden um akurate testdaten zu generieren
		switch(n) {
		case 0:
			multiplier = 1.0;	//burgenland
			state="Burgenland";
			break;
		case 1:
			multiplier = 2.4; 	//kaernten
			state="Kaernten";
			break;
		case 2:
			multiplier = 5.0;	//niederoesterreich
			state="Niederoesterreich";
			break;
		case 3:
			multiplier = 6.2;	//oberoesterreich
			state="Oberoesterreich";
			break;
		case 4:
			multiplier = 1.6;	//salzburg
			state="Salzburg";
			break;
		case 5:
			multiplier = 4.4;	//steiermark
			state="Steiermark";
			break;
		case 6:
			multiplier = 2.1;	//tirol
			state="Tirol";
			break;
		case 7:
			multiplier = 1.3;	//vorarlberg
			state="Vorarlberg";
			break;
		case 8:
			multiplier = 4.2;	//wien
			state="Wien";
			break;
		}
		
		
		// stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport
		
		// stromverbrauch:
		int minstromverbrauch = 300000;
		int maxstromverbrauch = 500000;
		double stromverbrauch = Math.floor(Math.random()*(maxstromverbrauch-minstromverbrauch+1)+minstromverbrauch)/10000 * multiplier;	
		stromverbrauch =((double)((int)(stromverbrauch *10000.0)))/10000.0; //Cuttet �berfl�ssige Dezimalstellen
		System.out.println("Stromverbrauch von "+ state + ": "+stromverbrauch+"PJ");
		
		// strompreis:
		int minstrompreis = 12;
		int maxstrompreis = 20;
		int strompreis = (int) Math.floor(Math.random()*(maxstrompreis-minstrompreis+1)+minstrompreis);	
		System.out.println("Strompreis von "+ state + ": "+strompreis+"c/kWh");
		
		//CO2-Emissionen
		int minemission = 50000;
		int maxemission = 100000;
		double emission = Math.floor(Math.random()*(maxemission-minemission+1)+minemission)/10000 * multiplier*1.1;	
		emission =((double)((int)(emission *10000.0)))/10000.0;
		System.out.println("CO2-Emissionen von "+ state + ": "+emission+"g/kWh");
		
		// Stromimport
		int minstromimport = 29000;
		int maxstromimport = 60000;
		double stromimport = Math.floor(Math.random()*(maxstromimport-minstromimport+1)+minstromimport)/10000 * multiplier;	
		stromimport =((double)((int)(stromimport *10000.0)))/10000.0;
		System.out.println("Stromimport von "+ state + ": "+stromimport+"GWh");
		
		// Stromexport
		int minstromexport = 20000;
		int maxstromexport = 30000;
		double stromexport = Math.floor(Math.random()*(maxstromexport-minstromexport+1)+minstromexport)/10000 * (multiplier*0.9);	
		stromexport =((double)((int)(stromexport *10000.0)))/10000.0;
		System.out.println("Stromexport von "+ state + ": "+stromexport+"GWh");
		
		String result="UPDATE testdata SET stromverbrauch='"+stromverbrauch+"',strompreis='"+strompreis+
				"',CO2Emissionen="+emission+",Stromimport="+stromimport+",Stromexport="+stromexport+
				"WHERE date="+date+" AND region='"+state+"'";
		System.out.println(result);
		return result;
	}
	public static String generateSum(ResultSet verbrauch,ResultSet strompreis,ResultSet emission,ResultSet stromimport, ResultSet stromexport, int date){
		try {
			// Verbrauch:
			double verbrauchSUM = 0.0;
			while(verbrauch.next()) {
				verbrauchSUM+=verbrauch.getDouble(1);
			}

			// Strompreis
			int strompreisSUM = 0;
			while(strompreis.next()) {
				strompreisSUM+=verbrauch.getInt(1);
			}

			// CO2Emission
			double emissionSUM = 0.0;
			while(emission.next()) {
				emissionSUM+=verbrauch.getDouble(1);
			}

			// Stromimport
			double importSUM = 0.0;
			while(stromimport.next()) {
				importSUM+=verbrauch.getDouble(1);
			}

			// Stromexport
			double exportSUM = 0.0;
			while(stromexport.next()) {
				exportSUM+=verbrauch.getDouble(1);
			}
			String result="INSERT INTO testdata " + "VALUES ('Oesterreich',100.0,"+verbrauchSUM+","+strompreisSUM+","+emissionSUM+","+importSUM+","+exportSUM+","+date+")";
			System.out.println(result);
			return result;

		}catch(SQLException se){}
		return null;
	}
	public static String generateSumNew(ResultSet verbrauch,ResultSet strompreis,ResultSet emission,ResultSet stromimport, ResultSet stromexport, int date){
		try {
			// Verbrauch:
			double verbrauchSUM = 0.0;
			while(verbrauch.next()) {
				verbrauchSUM+=verbrauch.getDouble(1);
			}

			// Strompreis
			int strompreisSUM = 0;
			while(strompreis.next()) {
				strompreisSUM+=verbrauch.getInt(1);
			}

			// CO2Emission
			double emissionSUM = 0.0;
			while(emission.next()) {
				emissionSUM+=verbrauch.getDouble(1);
			}

			// Stromimport
			double importSUM = 0.0;
			while(stromimport.next()) {
				importSUM+=verbrauch.getDouble(1);
			}

			// Stromexport
			double exportSUM = 0.0;
			while(stromexport.next()) {
				exportSUM+=verbrauch.getDouble(1);
			}
			String result="UPDATE testdata SET stromverbrauch='"+verbrauchSUM+"',strompreis='"+strompreisSUM+
					"',CO2Emissionen="+emissionSUM+",Stromimport="+importSUM+",Stromexport="+exportSUM+
					"WHERE date="+date+" AND region='Oesterreich'";
			System.out.println(result);
			return result;

		}catch(SQLException se){}
		return null;
	}
}