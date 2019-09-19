package model;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Transactional
public class TuoteDAOTest {
	private TuoteDAO tuote = new TuoteDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		assertEquals("Testi-Tuote", tuote.readTuoteNimi("Testi-Tuote").getTuote_nimi(), "Tuotetta ei löytynyt.");
		tuote.deleteTuote("Testi-Tuote");
	}

	@Test
	void testUpdateTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		tuote.updateTuote("Testi-Tuote", "Uusi nimi", "uusi yksikkö", 2);
		assertEquals("uusi yksikkö", tuote.readTuoteNimi("Uusi nimi").getTuote_yksikko(), "Tuotetta ei löytynyt.");
		tuote.deleteTuote("Uusi nimi");
	}

	@Test
	void testDeleteTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		tuote.deleteTuote("Testi-Tuote");
		assertEquals("null", tuote.readTuoteNimi("Testi-Tuote"), "Tuotetta ei poistettu.");

	}

}
