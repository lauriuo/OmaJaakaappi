package model;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TuoteDAOTest {
	private static TuoteDAO tuote = new TuoteDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateTuote() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "yksikko", 1);
		assertEquals(tuote_nimi, tuote.readTuoteNimi(tuote_nimi).getTuote_nimi(), "Tuotetta ei löytynyt.");
	}

	@Test
	void testUpdateTuote() {
		String tuote_nimi = "Testi-Tuote";
		String uusi_nimi = "Uusi tuote";
		String tuote_yksikko = "Kpl";
		String uusi_yksikko = "Uusi yksikkö";
		tuote.createTuote(tuote_nimi, tuote_yksikko, 1);
		tuote.updateTuote(tuote_nimi, uusi_nimi, uusi_yksikko, 2);
		assertEquals(uusi_yksikko, tuote.readTuoteNimi(uusi_nimi).getTuote_yksikko(), "Tuotetta ei löytynyt.");
	}

	@Test
	void testDeleteTuote() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "yksikko", 1);
		tuote.deleteTuote(tuote_nimi);
		assertEquals(null, tuote.readTuoteNimi(tuote_nimi), "Tuotetta ei poistettu.");

	}

}
