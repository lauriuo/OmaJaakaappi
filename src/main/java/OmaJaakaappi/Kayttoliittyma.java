package OmaJaakaappi;

import java.util.ArrayList;
import java.util.Scanner;

import model.TuoteDAO;

public class Kayttoliittyma {
	static TuoteDAO tuote = new TuoteDAO();
	static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {  
    	String purkkafiksi;
        char valinta;
		final char READALL = '1', READONE = '2', CREATE = '3', UPDATE = '4', DELETE = '5', QUIT = '6';
		
        do {
			System.out.print("1: Hae kaikki tuotteet.\n" +
							 "2: Hae tuote ID:llä.\n" +
							 "3: Lisää tuote.\n" +
							 "4: Muokkaa tuotetta.\n" +
							 "5: Poista tuote.\n" +
							 "6. Lopeta ohjelma.\n" +
							 "Valintasi: ");
			valinta = scanner.nextLine().charAt(0);
			switch (valinta) {
			case READALL:
				ArrayList<Object> tuotteet = tuote.readTuotteet();
		        for (Object t : tuotteet) {
		        	System.out.println(t);
		        }
		        System.out.println("-----------------------------------------");
				break;
			case READONE:
				System.out.print("Kirjoita tuotteen ID: ");
				int tuote_id = scanner.nextInt();
				System.out.println(tuote.readTuote(tuote_id));
				purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------korjaa
				System.out.println("-----------------------------------------");
				break;
			case CREATE:
				System.out.println("Kirjoita tuotteen nimi: ");
				String tuote_nimi = scanner.nextLine();
				System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
				String tuote_yksikko = scanner.nextLine();
				System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
				int tuote_kcal = scanner.nextInt();
				purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------korjaa
				 if (tuote.createTuote(tuote_nimi, tuote_yksikko, tuote_kcal) == true) {
					 System.out.println("Tuote lisätty.");
				 } else {
					 System.out.println("Tuotetta ei lisätty.");
				 }
			        System.out.println("-----------------------------------------");
				break;
			case UPDATE:
				System.out.println("Kirjoita tuotteen nimi: ");
				String vanha_nimi = scanner.nextLine();
				System.out.println("Kirjoita tuotteen uusi nimi: ");
				String uusi_nimi = scanner.nextLine();
				System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
				String uusi_yksikko = scanner.nextLine();
				System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
				int uusi_kcal = scanner.nextInt();
				purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------korjaa
				tuote.updateTuote(vanha_nimi, uusi_nimi, uusi_yksikko, uusi_kcal);
				System.out.println("-----------------------------------------");
				break;
			case DELETE:
				System.out.println("Kirjoita poistettavan tuotteen nimi: ");
				String poistettava_tuote = scanner.nextLine();
				tuote.deleteTuote(poistettava_tuote);
				System.out.println("-----------------------------------------");
				break;
			}
        } while (valinta != QUIT);
    }
}
