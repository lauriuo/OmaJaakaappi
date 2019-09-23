package OmaJaakaappi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import model.JaakaappiDAO;
import model.TuoteDAO;

public class Kayttoliittyma {
	static TuoteDAO tuote = new TuoteDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {  
    	String purkkafiksi;
        int valinta;
		final int TUOTEHAE = 1, TUOTEID = 2, TUOTENIMI = 3, TUOTELISAA = 4, TUOTEPAIVITA = 5, TUOTEPOISTA = 6, 
				JKHAE = 7, JKHAEID = 8, JKHAENIMI = 9, JKLISAA = 10, JKPAIVITA = 11, JKUPDATESTATUS = 12, JKPOISTA = 13, QUIT = 14;
		
        do {
			System.out.print("1: Hae kaikki tuotteet.\n" +
							 "2: Hae tuote ID:llä.\n" +
							 "3: Hae tuote nimellä.\n" +
							 "4: Lisää tuote.\n" +
							 "5: Muokkaa tuotetta.\n" +
							 "6: Poista tuote.\n" +
							 "7: Hae jääkaapin sisältö.\n" +
							 "8: Hae jääkaapissa olevia tuotteita ID:llä.\n" +
							 "9: Hae jääkaapissa olevia tuotteita nimellä.\n" +
							 "10: Lisää jääkaappiin tuotteita.\n" +
							 "11: Muokkaa jääkaapissa olevia tuotteita.\n" +
							 "12: Muokkaa jääkaapissa olevien tuotteiden statusta.\n" +
							 "13: Poista jääkaapissa olevia tuotteita.\n" +
							 "14. Lopeta ohjelma.\n" +
							 "Valintasi: ");
			valinta = scanner.nextInt();
			switch (valinta) {
			case TUOTEHAE:
				ArrayList<Object> tuotteet = tuote.readTuotteet();
		        for (Object t : tuotteet) {
		        	System.out.println(t);
		        }
		        System.out.println("-----------------------------------------");
				break;
			case TUOTEID:
				System.out.print("Kirjoita tuotteen ID: ");
				int tuote_id = scanner.nextInt();
				System.out.println(tuote.readTuote(tuote_id));
				System.out.println("-----------------------------------------");
				break;
			case TUOTENIMI:
				System.out.print("Kirjoita tuotteen nimi: ");
				String hae_nimi = scanner.nextLine();
				System.out.println(tuote.readTuoteNimi(hae_nimi));
				System.out.println("-----------------------------------------");
				break;
			case TUOTELISAA:
				System.out.println("Kirjoita tuotteen nimi: ");
				String tuote_nimi = scanner.next();
				System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
				String tuote_yksikko = scanner.next();
				System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
				int tuote_kcal = scanner.nextInt();
				 if (tuote.createTuote(tuote_nimi, tuote_yksikko, tuote_kcal) == true) {
					 System.out.println("Tuote lisätty.");
				 } else {
					 System.out.println("Tuotetta ei lisätty.");
				 }
			        System.out.println("-----------------------------------------");
				break;
			case TUOTEPAIVITA:
				System.out.println("Kirjoita tuotteen nimi: ");
				String vanha_nimi = scanner.next();
				System.out.println("Kirjoita tuotteen uusi nimi: ");
				String uusi_nimi = scanner.next();
				System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
				String uusi_yksikko = scanner.next();
				System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
				double uusi_kcal = scanner.nextDouble();
				tuote.updateTuote(vanha_nimi, uusi_nimi, uusi_yksikko, uusi_kcal);
				System.out.println("-----------------------------------------");
				break;
			case TUOTEPOISTA:
				System.out.println("Kirjoita poistettavan tuotteen nimi: ");
				String poistettava_tuote = scanner.nextLine();
				tuote.deleteTuote(poistettava_tuote);
				System.out.println("-----------------------------------------");
				break;
			case JKHAE:
				ArrayList<Object> jkt = jaakaappi.readJaakaapit();
		        for (Object t : jkt) {
		        	System.out.println(t);
		        }
		        System.out.println("-----------------------------------------");
				break;
			case JKHAEID:
				System.out.println("Kirjoita haettavan jääkaapissa olevan tuotteen ID: ");
				int jaakaappi_id = scanner.nextInt();
				System.out.println(jaakaappi.readJaakaappiId(jaakaappi_id));
				System.out.println("-----------------------------------------");
				break;
			case JKHAENIMI:
				System.out.print("Kirjoita jääkaapissa olevan tuotteen nimi: ");
				String jk_nimi = scanner.nextLine();
				System.out.println(jaakaappi.readJaakaappiNimi(jk_nimi));
				System.out.println("-----------------------------------------");
				break;
			case JKLISAA:
				System.out.println("Kirjoita tuotteen ID: ");
				tuote_id = scanner.nextInt();
				System.out.println("Kirjoita tuotteen määrä: ");
				double maara = scanner.nextInt();
				System.out.println("Kirjoita tuotteen viimeinen päivämäärä(muodossa 2019-05-23): ");
				String pvm_string = scanner.next();
				purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------tarkista toimiiko ilman
				Date pvm = Date.valueOf(pvm_string);
				String status = "Käytettävissä";
				jaakaappi.createJaakaappi(pvm, maara, status, tuote_id);
				System.out.println("-----------------------------------------");
				break;
			case JKPAIVITA:
				System.out.println("Kirjoita jääkaapissa olevan tuotteen ID: ");
				jaakaappi_id = scanner.nextInt();
				System.out.println("Kirjoita tuotteen uusi viimeinen päivämäärä(muodossa 2019-05-23): ");
				pvm_string = scanner.next();
				Date uusi_pvm = Date.valueOf(pvm_string);
				System.out.println("Kirjoita jääkaapissa olevan tuotteen uusi määrä: ");
				double uusi_maara = scanner.nextDouble();
				System.out.println("Kirjoita jääkaapissa olevan tuotteen uusi status: ");
				String uusi_status = scanner.next();
				purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------tarkista toimiiko ilman
				jaakaappi.updateJaakaappi(jaakaappi_id, uusi_pvm, uusi_maara, uusi_status);
				System.out.println("-----------------------------------------");
				break;
			case JKUPDATESTATUS:
				System.out.println("Kirjoita jääkaapissa olevan tuotteen id: ");
				jaakaappi_id = scanner.nextInt();
				System.out.println("Kirjoita jääkaapissa olevan tuotteen uusi status: ");
				uusi_status = scanner.nextLine();
				jaakaappi.updateJkStatus(jaakaappi_id, uusi_status);
				System.out.println("-----------------------------------------");
				break;
			case JKPOISTA:
				System.out.println("Kirjoita jääkaapissa olevan poistettavan tuotteen ID: ");
				jaakaappi_id = scanner.nextInt();
				jaakaappi.deleteJaakaappi(jaakaappi_id);
				System.out.println("-----------------------------------------");
				break;
			}
        } while (valinta != QUIT);
    }
}
