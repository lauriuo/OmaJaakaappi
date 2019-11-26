package OmaJaakaappi;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        int alkuvalinta = 0;
    	int valinta = 0;
    	boolean vError = true;
    	boolean v1Error = true;
		final int QUIT = 0, TUOTEHAE = 1, TUOTEID = 2, TUOTENIMI = 3, TUOTELISAA = 4, TUOTEPAIVITA = 5, TUOTEPOISTA = 6,
				JKHAE = 7, JKHAEID = 8, JKHAENIMI = 9, JKLISAA = 10, JKPAIVITA = 11, JKUPDATEKAYTETTY = 12, JKPOISTA = 13,
				 JKHAEKAYTETTY = 14, JKHAEVANHENEVAT = 15, JKHAEVANHAT = 16, JKPAIVITASTATUS = 17, RESEPTIHAE = 18, RESEPTILISAA = 19,
				 RESEPTIPAIVITA = 20, RESEPTIPOISTA = 21, LASKEKCAL = 22;
				 ;

        do {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	LocalDateTime time = LocalDateTime.now();

	        while (vError) {
			try {
			valinta = 0;
			System.out.println("Kello on nyt: " + time.format(formatter) + "\n ---");
			System.out.print("Etusivu. Siirry toimintavalikkoihin (1-3) tai lopeta ohjelma (0) \n" +
							 "0. Lopeta ohjelma.\n" +
							 "1: Tuotteet.\n" +
							 "2: Jääkaappi.\n" +
							 "3: Reseptit.\n");
			alkuvalinta = scanner.nextInt();
			if (alkuvalinta < 0 || alkuvalinta > 4) {
				System.out.println("Virheellinen syöte. Valitse väliltä 0-3");
			}
			else {
				vError = false;
				v1Error = true;
			}
			}
			catch (InputMismatchException e) {
				System.out.println("Virheellinen syöte. Tee valinta numeroilla.");
				alkuvalinta = -1;
				scanner.next();
			}
			while (v1Error) {
			if (alkuvalinta == -1) {
				v1Error = false;
				vError = true;
			}
			if (alkuvalinta == 0) {
				valinta = 0;
				v1Error = false;
				vError = false;
			}
			if (alkuvalinta == 1) {
				System.out.print("0. Palaa etusivulle.\n" +
								 "1: Hae kaikki tuotteet.\n" +
								 "2: Hae tuote ID:llä.\n" +
								 "3: Hae tuote nimellä.\n" +
								 "4: Lisää tuote.\n" +
								 "5: Muokkaa tuotetta.\n" +
								 "6: Poista tuote.\n");
				boolean if1Error = true;
				while (if1Error) {
					try {
						System.out.print("Valintasi: ");
						int vif1 = scanner.nextInt();
						if ((vif1 >= 1 && vif1 <= 6)) {
							valinta = vif1;
							if1Error = false;
							v1Error = false;
						} else if (vif1 == 0) {
							if1Error = false;
							v1Error = false;
							vError = true;
						} else { System.out.print("Virheellinen syöte. Valitse 0 tai väliltä 1-6 \n"); }
					} catch (InputMismatchException e) {
						System.out.print("Virheellinen syöte. Valitse numeroilla \n");
						scanner.next();
					}
				}
			}
			if (alkuvalinta == 2) {
				System.out.print("0: Palaa etusivulle \n" +
								 "7: Hae jääkaapin sisältö.\n" +
								 "8: Hae jääkaapissa olevia tuotteita ID:llä.\n" +
								 "9: Hae jääkaapissa olevia tuotteita nimellä.\n" +
								 "10: Lisää jääkaappiin tuotteita.\n" +
								 "11: Muokkaa jääkaapissa olevia tuotteita.\n" +
								 "12: Merkitse jääkaapissa olleen tuote käytetyksi.\n" +
								 "13: Poista jääkaapissa olevia tuotteita.\n" +
								 "14: Valitse käytetyt tuotteet. \n" +
								 "15: Valitse vanhaksi menevät tuotteet jääkaapista. \n" +
								 "16: Näytä vanhaksi menneet tuotteet. \n" +
								 "17: Muuta osa tuotteista käytetyiksi / hävikiksi. \n");
				boolean if2Error = true;
				while (if2Error) {
					try {
						System.out.print("Valintasi: ");
						int vif2 = scanner.nextInt();
						if ((vif2 >= 7 && vif2 <= 17)) {
							valinta = vif2;
							if2Error = false;
							v1Error = false;
						} else if (vif2 == 0) {
							if2Error = false;
							v1Error = false;
							vError = true;
						} else { System.out.print("Virheellinen syöte. Valitse 0 tai väliltä 7-17 \n"); }
					} catch (InputMismatchException e) {
						System.out.print("Virheellinen syöte. Valitse numeroilla \n");
						scanner.next();
					}
				}
			}
			if (alkuvalinta == 3) {
				System.out.print("0: Palaa alkuvalikkoon \n" +
								 "18: Hae reseptit. \n" +
								 "19: Lisää resepti. \n" +
								 "20: Päivitä reseptiä. \n" +
								 "21: Poista resepti. \n" +
								 "22: Laske reseptin kalorit. \n");
				boolean if3Error = true;
				while (if3Error) {
					try {
						System.out.print("Valintasi: ");
						int vif3 = scanner.nextInt();
						if ((vif3 >= 18 && vif3 <= 22)) {
							valinta = vif3;
							if3Error = false;
							v1Error = false;
						} else if (vif3 == 0) {
							if3Error = false;
							v1Error = false;
							vError = true;
						} else { System.out.print("Virheellinen syöte. Valitse 0 tai väliltä 18-22 \n"); }
					} catch (InputMismatchException e) {
						System.out.print("Virheellinen syöte. Valitse numeroilla \n");
						scanner.next();
					}
				}
			}
			}
			switch (valinta) {
			case TUOTEHAE:
				ArrayList<Object> tuotteet = tuote.readTuotteet();
		        for (Object t : tuotteet) {
		        	System.out.println(t);
		        }
		        System.out.println("-----------------------------------------");
		        vError = true;
				break;
			case TUOTEID:
				int tuote_id;
				boolean tidError = true;
				while (tidError) {
				    try {
					System.out.print("Kirjoita tuotteen ID: ");
					tuote_id = scanner.nextInt();
					System.out.println(tuote.readTuote(tuote_id));
					System.out.println("-----------------------------------------");
					tidError = false;
					}
				    catch (InputMismatchException e) {
						 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
						 scanner.next();
						 try
						 {
						 int tlValinta = scanner.nextInt();
							if (onkoYksi(tlValinta))
						 	{}
						   else {tidError = false;}}
						 catch (InputMismatchException f) {scanner.next(); tidError = false;}
					 }
					}
					vError = true;
					break;
			case TUOTENIMI:
				System.out.print("Kirjoita tuotteen nimi: ");
				purkkafiksi = scanner.nextLine();
				String hae_nimi = scanner.nextLine();
				if (tuote.readTuoteNimi(hae_nimi).equals("null"))
				System.out.println(tuote.readTuoteNimi(hae_nimi));
				System.out.println("-----------------------------------------");
				vError = true;
				break;
			case TUOTELISAA:
				boolean tlError = true;
				while (tlError) {
				 try {
					System.out.println("Kirjoita tuotteen nimi: ");
					purkkafiksi = scanner.nextLine();
					String tuote_nimi = scanner.next();
					System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
					String tuote_yksikko = scanner.next();
					System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
					int tuote_kcal = scanner.nextInt();
					System.out.println("Kirjoita tuotteessa oleva suolamäärä per 100g/1dl: ");
					double tuote_suola = scanner.nextDouble();
					if (tuote.createTuote(tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) == true) {
						 System.out.println("Tuote lisätty.");
						 tlError = false;
						 vError = true;
					 } else {
						 System.out.println("Tuote on jo olemassa. Tuotetta ei lisätty.");
						 tlError = false;
						 vError = true;
					 }
				    System.out.println("-----------------------------------------");}
				 catch (InputMismatchException e) {
					 System.out.println("Virheellinen syöte. Tuotetta ei lisätty. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {tlError = false; vError = true; }}
					 catch (InputMismatchException f) {scanner.next(); tlError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case TUOTEPAIVITA:
				boolean tpError = true;
				while (tpError) {
				 try {
					System.out.println("Kirjoita tuotteen nimi: ");
					String vanha_nimi = scanner.next();
					System.out.println("Kirjoita tuotteen uusi nimi: ");
					String uusi_nimi = scanner.next();
					System.out.println("Kirjoita tuotteen mittana käytetty yksikkö: ");
					String uusi_yksikko = scanner.next();
					System.out.println("Kirjoita tuotteessa oleva kalorimäärä per 100g/1dl: ");
					double uusi_kcal = scanner.nextDouble();
					System.out.println("Kirjoita tuotteessa oleva suolamäärä per 100g/1dl: ");
					double uusi_suola = scanner.nextDouble();
					tuote.updateTuote(vanha_nimi, uusi_nimi, uusi_yksikko, uusi_kcal, uusi_suola);
					System.out.println("-----------------------------------------");
					tpError = false;}
				 catch (InputMismatchException e) {
						 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
						 scanner.next();
						 try
						 {
						 int tlValinta = scanner.nextInt();
							if (onkoYksi(tlValinta))
						 	{}
						   else {tpError = false; vError = true; }}
						 catch (InputMismatchException f) {scanner.next(); tpError = false; vError = true;}
					 }
					}
				vError = true;
				break;
			case TUOTEPOISTA:
				System.out.println("Kirjoita poistettavan tuotteen nimi: ");
				purkkafiksi = scanner.nextLine();
				String poistettava_tuote = scanner.nextLine();
				tuote.deleteTuote(poistettava_tuote);
				System.out.println("-----------------------------------------");
				vError = true;
				break;
			case JKHAE:
				ArrayList<Object> jkt = jaakaappi.readJaakaapit();
		        for (Object t : jkt) {
		        	System.out.println(t);
		        }
		        System.out.println("-----------------------------------------");
		        vError = true;
				break;
			case JKHAEID:
				int jaakaappi_id;
				boolean jkidError = true;
				while (jkidError) {
				try {
				System.out.println("Kirjoita haettavan jääkaapissa olevan tuotteen ID: ");
				jaakaappi_id = scanner.nextInt();
				System.out.println(jaakaappi.readJaakaappiId(jaakaappi_id));
				System.out.println("-----------------------------------------");
				jkidError = false; }
				catch (InputMismatchException e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {jkidError = false; vError = true;}}
					 catch (InputMismatchException f) {scanner.next(); jkidError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case JKHAENIMI:
				purkkafiksi = scanner.nextLine();
				System.out.println("Kirjoita jääkaapissa olevan tuotteen nimi: ");
				String jk_nimi = scanner.nextLine();
				System.out.println(jaakaappi.readJaakaappiNimi(jk_nimi));
				System.out.println("-----------------------------------------");
				vError = true;
				break;
			case JKLISAA:
				Date pvm;
				String pvm_string;
				boolean jkliError = true;
				while (jkliError) {
				 try {
					System.out.println("Kirjoita tuotteen ID: ");
					tuote_id = scanner.nextInt();
					System.out.println("Kirjoita tuotteen määrä: ");
					double maara = scanner.nextInt();
					System.out.println("Kirjoita tuotteen viimeinen päivämäärä(muodossa 2019-05-23): ");
					pvm_string = scanner.next();
					purkkafiksi = scanner.nextLine();	// <-----------------------------------------------------tarkista toimiiko ilman
					pvm = Date.valueOf(pvm_string);
					String status = "Käytettävissä";
					jaakaappi.createJaakaappi(pvm, maara, status, tuote_id);
					System.out.println("-----------------------------------------");
					jkliError = false;}
				 catch (Exception e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {jkliError = false; vError = true; }}
					 catch (InputMismatchException f) {scanner.next(); jkliError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case JKPAIVITA:
				boolean jkpaiError = true;
				while (jkpaiError) {
				 try {
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
					jkpaiError = false;}
				 catch (Exception e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {jkpaiError = false; vError = true;}}
					 catch (InputMismatchException f) {scanner.next(); jkpaiError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case JKUPDATEKAYTETTY:
				boolean jkupError = true;
				while (jkupError) {
				    try {
						System.out.println("Kirjoita käytetyn jääkaapissa oleen tuotteen id: ");
						jaakaappi_id = scanner.nextInt();
						jaakaappi.updateJkKaytetty(jaakaappi_id);
						System.out.println("-----------------------------------------");
						jkupError = false;}
				    catch (InputMismatchException e) {
						 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
						 scanner.next();
						 try
						 {
						 int tlValinta = scanner.nextInt();
							if (onkoYksi(tlValinta))
						 	{}
						   else {jkupError = false; vError = true;}}
						 catch (InputMismatchException f) {scanner.next(); jkupError = false; vError = true;}
					 }
					}
				vError = true;
				break;
			case JKPOISTA:
				boolean jkpoisError = true;
				while (jkpoisError) {
				    try {
						System.out.println("Kirjoita jääkaapissa olevan poistettavan tuotteen ID: ");
						jaakaappi_id = scanner.nextInt();
						jaakaappi.deleteJaakaappi(jaakaappi_id);
						System.out.println("-----------------------------------------");
						jkpoisError = false;}
				    catch (InputMismatchException e) {
						 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
						 scanner.next();
						 try
						 {
						 int tlValinta = scanner.nextInt();
							if (onkoYksi(tlValinta))
						 	{}
						   else {jkpoisError = false; vError = true;}}
						 catch (InputMismatchException f) {scanner.next(); jkpoisError = false; vError = true;}
					 }
					}
				vError = true;
				break;
			case JKHAEKAYTETTY:
				ArrayList<Object> kaytetty = jaakaappi.readUsedJaakaapit();
				System.out.println("Tuotteet jotka käytetty: ");
				for (Object t : kaytetty) {
					System.out.println(t);
				}
				vError = true;
				break;
			case JKHAEVANHENEVAT:
				System.out.println("2 päivän kuluessa vanhenevat tuotteet: ");
				ArrayList<Object> vanhenevat = jaakaappi.readGoingOldJaakaapit();
				for (Object t : vanhenevat) {
					System.out.println(t);
				}
				vError = true;
				break;
			case RESEPTIHAE:
				ArrayList<Object> reseptit = resepti.readReseptit();
		        for (Object r : reseptit) {
		        	System.out.println(r);
		        }
		        System.out.println("-----------------------------------------");
		        vError = true;
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
			    vError = true;
				break;
			case RESEPTIPAIVITA:
				boolean respaiError = true;
				while (respaiError) {
				 try {
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
						 System.out.println("Resepti on olemassa. Reseptiä ei päivitetty.");
					 }
				        System.out.println("-----------------------------------------");
				    respaiError = false;}
				 catch (InputMismatchException e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {respaiError = false; vError = true; }}
					 catch (InputMismatchException f) {scanner.next(); respaiError = false; vError = true;}
				 }
				}
			    vError = true;
				break;
			case RESEPTIPOISTA:
				boolean respoiError = true;
				while (respoiError) {
				 try {
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
					respoiError = false;}
				 catch (InputMismatchException e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {respoiError = false; vError = true; }}
					 catch (InputMismatchException f) {scanner.next(); respoiError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case JKHAEVANHAT:
				 ArrayList<Object> vanhat = jaakaappi.readWasteJaakaapit();
				System.out.println("Vanhaksi menneet tuotteet: ");
				for (Object v : vanhat) {
					System.out.println(v);
				}
				vError = true;
				break;
			case JKPAIVITASTATUS:
				boolean statpaiError = true;
				while (statpaiError) {
				 try {
					System.out.println("Valitse muutettavan tuotteen ID: \n");
					int muutettava_id = scanner.nextInt();
					purkkafiksi = scanner.nextLine();

					System.out.println("Muutetaanko tuote: \n 1. hävikiksi \n 2. käytetyksi");
					int havikki_vai_kaytetty = scanner.nextInt();
					String muutettava_status = "Käytettävissä";
					purkkafiksi = scanner.nextLine();
					if (havikki_vai_kaytetty == 1) {
						muutettava_status = "Hävikki";
					} else if (havikki_vai_kaytetty == 2) {
						muutettava_status = "Käytetty";
					}

					Jaakaappi muutettava_jaakaappi = jaakaappi.readJaakaappiId(muutettava_id);
					System.out.println("Kuinka paljon tuotteen " + muutettava_jaakaappi.getTuote().getTuote_nimi() +
										" määrästä " + muutettava_jaakaappi.getTuote_maara() + " " +
										muutettava_jaakaappi.getTuote().getTuote_yksikko() + " muutetaan? \n");
					double tuote_maara = scanner.nextDouble();
					purkkafiksi = scanner.nextLine();
					jaakaappi.updateJkMaara(muutettava_id, tuote_maara, muutettava_status);
					statpaiError = false;}
				 catch (InputMismatchException e) {
					 System.out.println("Virheellinen syöte. Yritä uudestaan (valitse 1) tai palaa etusivulle (valitse muu numero tai kirjain)");
					 scanner.next();
					 try
					 {
					 int tlValinta = scanner.nextInt();
						if (onkoYksi(tlValinta))
					 	{}
					   else {statpaiError = false; vError = true; }}
					 catch (InputMismatchException f) {scanner.next(); statpaiError = false; vError = true;}
				 }
				}
				vError = true;
				break;
			case LASKEKCAL:
				System.out.println("Kirjoita reseptin id: \n");
				int r_id = scanner.nextInt();
				double kcalit = resepti.countKcalResepti(r_id);
				System.out.print("reseptin koko kalorimäärä: " + kcalit + " kcal.");
				vError = true;
				break;
			}
        }
    } while (valinta != QUIT);
   System.out.println("Ohjelma sammutettu");
  }


public static boolean onkoYksi(int int1) {
	    if (int1 == 1) {
	    return true;
	    }
	    else {
	    return false; }
	}
}
