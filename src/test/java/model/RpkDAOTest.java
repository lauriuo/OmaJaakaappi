package model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RpkDAOTest {
	private static TuoteDAO tuote = new TuoteDAO();
	private static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	private static RpkDAO rpk = new RpkDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		rpk.emptyRpk();
		jaakaappi.emptyJaakaappi();
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		rpk.emptyRpk();
		jaakaappi.emptyJaakaappi();
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateRpk() {
		tuote.createTuote("Testi-Tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Testi-Tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		
		int jaakaappi_id = jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id();
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date rpk_pvm = Date.valueOf(time.format(formatter));
		
		assertEquals(true, rpk.createRpk(rpk_pvm, jaakaappi_id), "Ei toimi.");
	}

	@Test
	void testUpdateRpk() {
		tuote.createTuote("Testi-Tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Testi-Tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		
		int jaakaappi_id = jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id();
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date rpk_pvm = Date.valueOf(time.format(formatter));
		rpk.createRpk(rpk_pvm, jaakaappi_id);
		Date uusi_pvm = Date.valueOf("2019-09-30");
		int rpk_id = rpk.readRpkJkId(jaakaappi_id).getRpk_id();
		rpk.updateRpk(rpk_id, uusi_pvm);
		assertEquals(uusi_pvm, rpk.readRpkId(rpk_id).getRpk_pvm(), "Ei toimi.");
	}

	@Test
	void testDeleteRpk() {
		tuote.createTuote("Testi-Tuote", "kpl", 1);
		Date pvm = Date.valueOf("2019-09-28");
		int tuote_id = tuote.readTuoteNimi("Testi-Tuote").getTuote_id();
		jaakaappi.createJaakaappi(pvm, 5, "Käytettävissä", tuote_id);
		
		int jaakaappi_id = jaakaappi.readJaakaappi(tuote_id, pvm).getJaakaappi_id();
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date rpk_pvm = Date.valueOf(time.format(formatter));
		rpk.createRpk(rpk_pvm, jaakaappi_id);
		int rpk_id = rpk.readRpkJkId(jaakaappi_id).getRpk_id();
		rpk.deleteRpk(rpk_id);
		assertEquals(null, rpk.readRpkId(rpk_id));
	}

}
