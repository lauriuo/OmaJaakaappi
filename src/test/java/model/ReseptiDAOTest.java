package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReseptiDAOTest {
	private static ReseptiDAO resepti = new ReseptiDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		resepti.emptyResepti();
	}

	@BeforeEach
	void setUp() throws Exception {
		resepti.emptyResepti();
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
	
}
