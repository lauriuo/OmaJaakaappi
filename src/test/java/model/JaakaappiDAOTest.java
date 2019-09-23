package model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Transactional
class JaakaappiDAOTest {
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
	void testCreateJaakaappi() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		assertEquals(null, jaakaappi.readJaakaappi(tuote_id, pvm), "Tuotetta löytyy jo kyseisellä päivämäärällä.");
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		assertEquals(pvm, jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_pvm(), "Luonti epäonnistui.");
	}
	
	@Test
	void testSamaPvm() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-21");
		double maara1 = 5;
		double maara2 = 7;
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		assertEquals(null, jaakaappi.readJaakaappi(tuote_id, pvm), "Tuotetta löytyy jo kyseisellä päivämäärällä.");
		jaakaappi.createJaakaappi(pvm, maara1, "Käytettävissä", tuote_id);
		jaakaappi.createJaakaappi(pvm, maara2, "Käytettävissä", tuote_id);
		assertEquals(12, jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_maara(), "Määrä väärin.");
	}

	@Test
	void testReadJaakaapit() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		pvm = Date.valueOf("2019-09-29");
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		assertEquals(2, jaakaappi.readJaakaapit().size(), "Luku epäonnistui.");
	}

	@Test
	void testUpdateJaakaappi() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		Date uusi_pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		jaakaappi.updateJaakaappi(jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id(), uusi_pvm, 6, "Hävikki");
		assertEquals(uusi_pvm, jaakaappi.readJaakaappi(tuote_id, uusi_pvm).getTuote_pvm(), "Päivämäärä ei päivittynyt oikein.");
	}

	@Test
	void testUpdateJkStatus() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		jaakaappi.updateJkStatus(jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id(), "Hävikki");
		assertEquals("Hävikki", jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_status(), "Status ei päivittynyt oikein.");
	}

	@Test
	void testDeleteJaakaappi() {
		tuote.createTuote("Jk-tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		int tuote_id = tuote.readTuoteNimi("Jk-tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		int jaakaappi_id = jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id();
		jaakaappi.deleteJaakaappi(jaakaappi_id);
		assertEquals(null, jaakaappi.readJaakaappiId(jaakaappi_id));
	}

}
