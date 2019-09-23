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
	private static TuoteDAO tuote = new TuoteDAO();
	private static JaakaappiDAO jaakaappi = new JaakaappiDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		jaakaappi.emptyJaakaappi();
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		jaakaappi.emptyJaakaappi();
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		assertEquals("Testi-Tuote", tuote.readTuoteNimi("Testi-Tuote").getTuote_nimi(), "Tuotetta ei löytynyt.");
	}

	@Test
	void testUpdateTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		tuote.updateTuote("Testi-Tuote", "Uusi nimi", "uusi yksikkö", 2);
		assertEquals("uusi yksikkö", tuote.readTuoteNimi("Uusi nimi").getTuote_yksikko(), "Tuotetta ei löytynyt.");
	}

	@Test
	@Transactional(rollbackOn=Tuote.class)
	void testDeleteTuote() {
		tuote.createTuote("Testi-Tuote", "yksikko", 1);
		tuote.deleteTuote("Testi-Tuote");
		assertEquals(null, tuote.readTuoteNimi("Testi-Tuote"), "Tuotetta ei poistettu.");

	}

}
