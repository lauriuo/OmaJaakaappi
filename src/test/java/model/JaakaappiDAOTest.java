package model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		assertEquals(null, jaakaappi.readJaakaappi(tuote_id, pvm), "Tuotetta löytyy jo kyseisellä päivämäärällä.");
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		assertEquals(pvm, jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_pvm(), "Luonti epäonnistui.");
	}
	
	@Test
	void testSamaPvm() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-21");
		double maara1 = 5;
		double maara2 = 7;
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		assertEquals(null, jaakaappi.readJaakaappi(tuote_id, pvm), "Tuotetta löytyy jo kyseisellä päivämäärällä.");
		jaakaappi.createJaakaappi(pvm, maara1, "Käytettävissä", tuote_id);
		jaakaappi.createJaakaappi(pvm, maara2, "Käytettävissä", tuote_id);
		assertEquals(12, jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_maara(), "Määrä väärin.");
	}

	@Test
	void testReadJaakaapit() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		pvm = Date.valueOf("2019-09-29");
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		assertEquals(2, jaakaappi.readJaakaapit().size(), "Luku epäonnistui.");
	}

	@Test
	void testUpdateJaakaappi() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		Date uusi_pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		jaakaappi.updateJaakaappi(jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id(), uusi_pvm, 6, "Hävikki");
		assertEquals(uusi_pvm, jaakaappi.readJaakaappi(tuote_id, uusi_pvm).getTuote_pvm(), "Päivämäärä ei päivittynyt oikein.");
	}

	@Test
	void testUpdateJkKaytetty() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		jaakaappi.updateJkKaytetty(jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id());
		assertEquals("Käytetty", jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_status(), "Status ei päivittynyt oikein.");
	}
	
	@Test
	void testUpdateJkHavikki() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		jaakaappi.updateJkHavikki(jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id());
		assertEquals("Hävikki", jaakaappi.readJaakaappi(tuote_id, pvm).getTuote_status(), "Status ei päivittynyt oikein.");
	}

	@Test
	void testDeleteJaakaappi() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1);
		Date pvm = Date.valueOf("2019-09-27");
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		int jaakaappi_id = jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id();
		jaakaappi.deleteJaakaappi(jaakaappi_id);
		assertEquals(null, jaakaappi.readJaakaappiId(jaakaappi_id));
	}
	@Test
	void testGoingOldJaakaapit() {
		tuote.createTuote("aika1", "kpl", 1);
		tuote.createTuote("aika2", "kpl", 1);
		int aika1_id = tuote.readTuoteNimi("aika1").getTuote_id();
		int aika2_id = tuote.readTuoteNimi("aika2").getTuote_id();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus3days = LocalDateTime.now().plusDays(3);
		java.sql.Date sqltimenow = java.sql.Date.valueOf(now.toLocalDate());
		java.sql.Date sqltime3days = java.sql.Date.valueOf(plus3days.toLocalDate());

		jaakaappi.createJaakaappi(sqltimenow, 1, "Käytettävissä", aika1_id);
		jaakaappi.createJaakaappi(sqltime3days, 1, "Käytettävissä", aika2_id);

		assertEquals(1, jaakaappi.readGoingOldJaakaapit().size(), "Pitäis näkyä 1 tuote.");
	}
	@Test
	void testReadWasteJaakaapit() {
		tuote.createTuote("huono", "kpl", 1);
		tuote.createTuote("hyva", "kpl", 1);
		int huono_id = tuote.readTuoteNimi("huono").getTuote_id();
		int hyva_id = tuote.readTuoteNimi("hyva").getTuote_id();
		LocalDateTime now = LocalDateTime.now();
		java.sql.Date sql_aika = java.sql.Date.valueOf(now.toLocalDate());

		jaakaappi.createJaakaappi(sql_aika, 1, "Hävikki", huono_id);
		jaakaappi.createJaakaappi(sql_aika, 1, "Käytettävissä", hyva_id);

		assertEquals(1, jaakaappi.readWasteJaakaapit().size(), "Pitäis näkyä 1 tuote.");
	}
}
