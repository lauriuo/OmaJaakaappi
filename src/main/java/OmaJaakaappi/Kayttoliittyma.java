package OmaJaakaappi;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model.AinesDAO;
import model.Jaakaappi;
import model.JaakaappiDAO;
import model.ReseptiDAO;
import model.RpkDAO;
import model.TuoteDAO;

public class Kayttoliittyma {
	static TuoteDAO tuote = new TuoteDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	static RpkDAO rpk = new RpkDAO();
	static ReseptiDAO resepti = new ReseptiDAO();
	static AinesDAO aines = new AinesDAO();
	static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {  
    	String purkkafiksi;
        int valinta;
		final int QUIT = 0, TUOTEHAE = 1, TUOTEID = 2, TUOTENIMI = 3, TUOTELISAA = 4, TUOTEPAIVITA = 5, TUOTEPOISTA = 6, 
				JKHAE = 7, JKHAEID = 8, JKHAENIMI = 9, JKLISAA = 10, JKPAIVITA = 11, JKUPDATEKAYTETTY = 12, JKPOISTA = 13,
				 JKHAEKAYTETTY = 14, JKHAEVANHENEVAT = 15, RESEPTIHAE = 16, RESEPTILISAA = 17, RESEPTIPAIVITA = 18, RESEPTIPOISTA = 19,
				 JKHAEVANHAT = 20, JKPAIVITASTATUS = 21;
		
        do {
        	LocalDateTime time = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			System.out.println("Kello on nyt: " + time.format(formatter));
			System.out.print("0. Lopeta ohjelma.\n" +
  							 "1: Hae kaikki tuotteet.\n" +
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
							 "12: Merkitse jääkaapissa olleen tuote käytetyksi.\n" +
							 "13: Poista jääkaapissa olevia tuotteita.\n" +
							 "14: Valitse käytetyt tuotteet. \n" +
							 "15: Valitse vanhaksi menevät tuotteet jääkaapista. \n" +
							 "16: Hae reseptit. \n" +
							 "17: Lisää resepti. \n" +
							 "18: Päivitä reseptiä. \n" +
							 "19: Poista resepti. \n" +
							 "20: Näytä vanhaksi menneet tuotteet. \n" +
							 "21: Muuta osa tuotteista käytetyiksi / hävikiksi. \n" +
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
			case JKUPDATEKAYTETTY:
				System.out.println("Kirjoita käytetyn jääkaapissa oleen tuotteen id: ");
				jaakaappi_id = scanner.nextInt();
				jaakaappi.updateJkKaytetty(jaakaappi_id);
				System.out.println("-----------------------------------------");
				break;
			case JKPOISTA:
				System.out.println("Kirjoita jääkaapissa olevan poistettavan tuotteen ID: ");
				jaakaappi_id = scanner.nextInt();
				jaakaappi.deleteJaakaappi(jaakaappi_id);
				System.out.println("-----------------------------------------");
				break;
			case JKHAEKAYTETTY:
				ArrayList<Object> kaytetty = jaakaappi.readUsedJaakaapit();
				System.out.println("Tuotteet jotka käytetty: ");
				for (Object t : kaytetty) {
					System.out.println(t);	
				}
				break;
			case JKHAEVANHENEVAT:
				System.out.println("2 päivän kuluessa vanhenevat tuotteet: ");
				ArrayList<Object> vanhenevat = jaakaappi.readGoingOldJaakaapit();
				for (Object t : vanhenevat) {
					System.out.println(t);
				}
				break;
			case RESEPTIHAE:
				ArrayList<Object> reseptit = resepti.readReseptit();
		        for (Object r : reseptit) {
		        	System.out.println(r);
		        }
		        System.out.println("-----------------------------------------");
				break;
			case RESEPTILISAA:
				purkkafiksi = scanner.nextLine();
				System.out.println("Kirjoita reseptit nimi: ");
				String resepti_nimi = scanner.nextLine();
				purkkafiksi = scanner.nextLine();
				System.out.println("Kirjoita reseptin ohje: ");
				String resepti_ohje = scanner.nextLine();
				 if (resepti.createResepti(resepti_nimi, resepti_ohje) == true) {
					 System.out.println("Resepti lisätty.");
				 } else {
					 System.out.println("Reseptiä ei lisätty.");
				 }
			        System.out.println("-----------------------------------------");
				break;
			case RESEPTIPAIVITA:
				purkkafiksi = scanner.nextLine();
				System.out.println("Kirjoita reseptin ID: ");
				int resepti_id = scanner.nextInt();
				System.out.println("Kirjoita reseptin uusi nimi: ");
				String resepti_uusi_nimi = scanner.nextLine();
				purkkafiksi = scanner.nextLine();
				System.out.println("Kirjoita reseptin uusi ohje: ");
				String resepti_uusi_ohje = scanner.nextLine();
				purkkafiksi = scanner.nextLine();
				 if (resepti.updateResepti(resepti_id, resepti_uusi_nimi, resepti_uusi_ohje) == true) {
					 System.out.println("Resepti päivitetty.");
				 } else {
					 System.out.println("Reseptiä ei päivitetty.");
				 }
			        System.out.println("-----------------------------------------");
				break;
			case RESEPTIPOISTA:
				System.out.println("Kirjoita poistettavan reseptin ID: ");
				int poistettava_resepti = scanner.nextInt();
				if (aines.deleteAineksetResepti(poistettava_resepti) == true) {
					if (resepti.deleteResepti(poistettava_resepti) == true) {
						System.out.println("Resepti poistettu aineksineen.");
					} else {
						System.out.println("Reseptin ainekset poistettiin, mutta reseptiä ei poistettu.");
					}
				 } else {
					System.out.println("Reseptiä ei poistettu.");
				 }
				System.out.println("-----------------------------------------");
				break;
			case JKHAEVANHAT:
				 ArrayList<Object> vanhat = jaakaappi.readWasteJaakaapit();
				System.out.println("Vanhaksi menneet tuotteet: ");
				for (Object v : vanhat) {
					System.out.println(v);	
				}
				break;
			case JKPAIVITASTATUS:
				System.out.println("Kirjoita muutettavan tuotteen nimi: ");
				String muutettava_tuote = scanner.next();
				ArrayList<Object> nimi_tuotteet = jaakaappi.readJaakaappiNimi(muutettava_tuote);

				if (nimi_tuotteet.isEmpty()) {
					System.out.println("Ei tuotetta Jääkaapissa nimellä " + muutettava_tuote + ".\n");
				} else {
					System.out.println("Tuotteet nimellä " + muutettava_tuote + ": \n");
					for (Object t : nimi_tuotteet) {
						System.out.println(t);	
					}
					System.out.println("Valitse muutettavan tuotteen ID: \n");
					int muutettava_id = scanner.nextInt();
					purkkafiksi = scanner.nextLine();
					System.out.println("Muutetaanko tuote: \n 1. hävikiksi \n 2. käytetyksi");
					int havikki_vai_kaytetty = scanner.nextInt();
					purkkafiksi = scanner.nextLine();
					Jaakaappi muutettava_jaakaappi = jaakaappi.readJaakaappiId(muutettava_id);
					System.out.println("Kuinka paljon tuotteen " + muutettava_jaakaappi.getTuote().getTuote_nimi() + 
										" määrästä " + muutettava_jaakaappi.getTuote_maara() + " muutetaan? \n"); 
					double tuote_maara = scanner.nextDouble();
					purkkafiksi = scanner.nextLine();
					double maara_erotus = muutettava_jaakaappi.getTuote_maara() -  tuote_maara;

					//Jos muutettavasta tuotteesta valitaan vain osa, luodaan toinen uusi tuote jääkaappiin.
					//Eli jos erotus menee miinukselle, se tarkoittaa että vain koko tuote muutetaan, ei tarvitse uutta tuotetta.
					if (maara_erotus <= 0) {
						if (havikki_vai_kaytetty == 1) {
							jaakaappi.updateJkHavikki(muutettava_id);
						} else if (havikki_vai_kaytetty == 2) {
							jaakaappi.updateJkKaytetty(muutettava_id);
						}
					} else {
						muutettava_jaakaappi.setTuote_maara(maara_erotus);
						String tyyppi_string = "Käytettävissä";
						if (havikki_vai_kaytetty == 1) {
							tyyppi_string = "Hävikki";
						} else if (havikki_vai_kaytetty == 2) {
							tyyppi_string = "Käytetty";
						}
						jaakaappi.createJaakaappi(muutettava_jaakaappi.getTuote_pvm(), tuote_maara,
												tyyppi_string, muutettava_jaakaappi.getTuote().getTuote_id());
						jaakaappi.updateJaakaappi(muutettava_jaakaappi.getJaakaappi_id(), muutettava_jaakaappi.getTuote_pvm(),
												maara_erotus, muutettava_jaakaappi.getTuote_status());
						}
					}
				break;
			}
        } while (valinta != QUIT);
    }
}
