package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReseptiDAOTest {
	private static ReseptiDAO resepti = new ReseptiDAO();
	private static TuoteDAO tuote = new TuoteDAO();
	private static AinesDAO aines = new AinesDAO();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		aines.emptyAines();
		resepti.emptyResepti();
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		aines.emptyAines();
		resepti.emptyResepti();
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateResepti() {
		String nimi = "Testi-Resepti";
		resepti.createResepti(nimi, "1. Tee näin. 2. Tee noin.");
		assertEquals(nimi, resepti.readReseptiNimi(nimi).getResepti_nimi(), "Luonti ei onnistunut. ");
	}

	@Test
	void testReadReseptiId() {
		String ohje = "1. Tee näin. 2. Tee noin.";
		resepti.createResepti("Testi-Resepti", ohje);
		int resepti_id = resepti.readReseptiNimi("Testi-Resepti").getResepti_id();
		assertEquals(ohje, resepti.readReseptiId(resepti_id).getResepti_ohje(), "Hake ei onnistunut.");
	}

	@Test
	 void testUpdateResepti() {
		String vanha_ohje = "1. Tee näin. 2. Tee noin.";
		String uusi_ohje = "1. Tee se. 2. Tee tää.";
		String vanha_nimi = "Testi-Resepti";
		String uusi_nimi = "Uusi-Resepti";
		resepti.createResepti(vanha_nimi, vanha_ohje);
		resepti.updateResepti(resepti.readReseptiNimi(vanha_nimi).getResepti_id(), uusi_nimi, uusi_ohje);
		assertEquals(uusi_nimi, resepti.readReseptiNimi(uusi_nimi).getResepti_nimi(), "Nimi ei päivittynyt oikein.");
		assertEquals(uusi_ohje, resepti.readReseptiNimi(uusi_nimi).getResepti_ohje(), "Ohje ei päivittynyt oikein.");
	} 

	@Test
	void testDeleteResepti() {
		String nimi = "Testi-Resepti";
		resepti.createResepti(nimi, "1. Tee näin. 2. Tee noin.");
		resepti.deleteResepti(resepti.readReseptiNimi(nimi).getResepti_id());
		assertEquals(null, resepti.readReseptiNimi(nimi), "Tuotetta ei poistettu.");
	}
	@Test
	void testCountKcalResepti() {
		resepti.createResepti("Kcal-Testi", "1. Tee näin. 2. Tee noin.");
		Resepti id_resepti = resepti.readReseptiNimi("Kcal-Testi");

		tuote.createTuote("Tuote1", "kpl", 200, 1);
		tuote.createTuote("Tuote2", "kpl", 400, 1);
		Tuote id_tuote1 = tuote.readTuoteNimi("Tuote1");
		Tuote id_tuote2 = tuote.readTuoteNimi("Tuote2");
		aines.createAines(id_tuote1.getTuote_id(), id_resepti.getResepti_id(), 2);
		aines.createAines(id_tuote2.getTuote_id(), id_resepti.getResepti_id(), 2);
		double kcals = resepti.countKcalResepti(id_resepti.getResepti_id());
		aines.deleteAineksetResepti(id_resepti.getResepti_id());
		resepti.deleteResepti(id_resepti.getResepti_id());
		tuote.deleteTuote("Tuote1");
		tuote.deleteTuote("Tuote2");
		assertEquals(12000.0, kcals, "Pitäis olla 12000.");
	}
}
