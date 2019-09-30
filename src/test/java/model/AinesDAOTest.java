package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AinesDAOTest {
	private static AinesDAO aines = new AinesDAO();
	private static TuoteDAO tuote = new TuoteDAO();
	private static ReseptiDAO resepti = new ReseptiDAO();
	private static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	private static RpkDAO rpk = new RpkDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		aines.emptyAines();
		rpk.emptyRpk();
		jaakaappi.emptyJaakaappi();
		resepti.emptyResepti();
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		aines.emptyAines();
		rpk.emptyRpk();
		jaakaappi.emptyJaakaappi();
		resepti.emptyResepti();
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateAines() {
		String tuote_nimi = "Testi-Tuote";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		assertEquals(5, aines.readAines(tuote_nimi, resepti_nimi).getAines_maara(), "Ainesta ei luotu oikein.");
	}
	
	@Test
	void testSamaTR() {
		String tuote_nimi = "Testi-Tuote";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		assertEquals(10, aines.readAines(tuote_nimi, resepti_nimi).getAines_maara(), "Aineksen määrä ei päivittynyt oikein.");
	}

	@Test
	void testReadAineksetReseptiID() {
		String tuote_nimi = "Testi-Tuote";
		String tuote_nimi2 = "Testi-Tuote2";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		tuote.createTuote(tuote_nimi2, "Kpl", 2);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		aines.createAines(tuote.readTuoteNimi(tuote_nimi2).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 4);
		assertEquals(2, aines.readAineksetReseptiID(resepti.readReseptiNimi(resepti_nimi).getResepti_id()).size(), "Haku ei onnistunut oikein.");
	}

	@Test
	void testReadAineksetReseptiNimi() {
		String tuote_nimi = "Testi-Tuote";
		String tuote_nimi2 = "Testi-Tuote2";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		tuote.createTuote(tuote_nimi2, "Kpl", 2);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		aines.createAines(tuote.readTuoteNimi(tuote_nimi2).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 4);
		assertEquals(2, aines.readAineksetReseptiNimi(resepti_nimi).size(), "Haku ei onnistunut oikein.");
	}

	@Test
	void testReadAinesReseptit() {
		String tuote_nimi = "Testi-Tuote";
		String resepti_nimi = "Testi-Resepti";
		String resepti_nimi2 = "Testi-Resepti2";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		resepti.createResepti(resepti_nimi2, "1: QWE. 2: EWQ.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi2).getResepti_id(), 4);
		assertEquals(2, aines.readAinesReseptit(tuote_nimi).size(), "Haku ei onnistunut oikein.");
	}

	@Test
	void testUpdateAines() {
		String tuote_nimi = "Testi-Tuote";
		String tuote_nimi2 = "Uusi tuote";
		String resepti_nimi = "Testi-Resepti";
		String resepti_nimi2 = "Uusi resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		tuote.createTuote(tuote_nimi2, "Kpll", 11);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		resepti.createResepti(resepti_nimi2, "1: QWE. 2: EWQ.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		int aines_id = aines.readAines(tuote_nimi, resepti_nimi).getAines_id();
		aines.updateAines(aines_id, tuote_nimi2, resepti_nimi2, 4);
		assertEquals(4, aines.readAinesId(aines_id).getAines_maara(), "Aineksen määrä ei päivittynyt oikein.");
		assertEquals(tuote_nimi2, aines.readAinesId(aines_id).getTuote().getTuote_nimi(), "Aineksen tuote ei päivittynyt oikein.");
		assertEquals(resepti_nimi2, aines.readAinesId(aines_id).getResepti().getResepti_nimi(), "Aineksen resepti ei päivittynyt oikein.");
	}

	@Test
	void testDeleteAines() {
		String tuote_nimi = "Testi-Tuote";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		int aines_id = aines.readAines(tuote_nimi, resepti_nimi).getAines_id();
		aines.deleteAines(aines_id);
		assertEquals(null, aines.readAinesId(aines_id), "Aineksia ei poistettu oikein.");
	}

	@Test
	void testDeleteAineksetResepti() {
		String tuote_nimi = "Testi-Tuote";
		String tuote_nimi2 = "Testi-Tuote2";
		String resepti_nimi = "Testi-Resepti";
		tuote.createTuote(tuote_nimi, "Kpl", 1);
		tuote.createTuote(tuote_nimi2, "Kpl", 2);
		resepti.createResepti(resepti_nimi, "1: ASD. 2: DSA.");
		aines.createAines(tuote.readTuoteNimi(tuote_nimi).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 5);
		aines.createAines(tuote.readTuoteNimi(tuote_nimi2).getTuote_id(), resepti.readReseptiNimi(resepti_nimi).getResepti_id(), 6);
		aines.deleteAineksetResepti(resepti.readReseptiNimi(resepti_nimi).getResepti_id());
		assertEquals(0, aines.readAineksetReseptiNimi(resepti_nimi).size(), "Aineksia ei poistettu oikein.");
	}
	
}
