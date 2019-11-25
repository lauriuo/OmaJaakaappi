package model;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OstoslistaDAOTest {
	private static TuoteDAO tuote = new TuoteDAO();
	private static OstoslistaDAO ostoslista = new OstoslistaDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ostoslista.emptyOstoslista();
		tuote.emptyTuote();
	}

	@BeforeEach
	void setUp() throws Exception {
		ostoslista.emptyOstoslista();
		tuote.emptyTuote();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateOstoslista() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1, 1);
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		assertEquals(null, ostoslista.readOstoslista(tuote_id), "Tuotetta löytyy jo kyseisellä päivämäärällä.");
		ostoslista.createOstoslista(5, tuote_id);
		assertEquals(5, ostoslista.readOstoslista(tuote_id).getTuote_maara(), "Luonti epäonnistui.");
	}
	
	@Test
	void testReadOstoslistat() {
        String tuote_nimi = "Testi-Tuote";
		String tuote_nimi2 = "Testi-Tuote2";
        tuote.createTuote(tuote_nimi, "kpl", 1, 1);
		tuote.createTuote(tuote_nimi2, "kpl", 1, 1);
        int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		int tuote_id2 = tuote.readTuoteNimi(tuote_nimi2).getTuote_id();
		ostoslista.createOstoslista(5, tuote_id);
		ostoslista.createOstoslista(5, tuote_id2);
		assertEquals(2, ostoslista.readOstoslistat().size(), "Luku epäonnistui.");
    }
    @Test
    void testReadOstoslistaName() {
         String tuote_nimi = "Testi-Tuote";
        tuote.createTuote(tuote_nimi, "kpl", 1, 1);       
        int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
        ostoslista.createOstoslista(5, tuote_id);
        assertEquals(1, ostoslista.readOstoslistaName("Testi-Tuote").size(), "Pitäisi olla 1 tuote");
    }
	@Test
	void testDeleteOstoslista() {
		String tuote_nimi = "Testi-Tuote";
		tuote.createTuote(tuote_nimi, "kpl", 1, 1);
		int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
		ostoslista.createOstoslista(5, tuote_id);
		int ostoslista_id = ostoslista.readOstoslista(tuote_id).getOstoslista_id();
		ostoslista.deleteOstoslista(ostoslista_id);
		assertEquals(null, ostoslista.readOstoslistaId(ostoslista_id));
	}
}