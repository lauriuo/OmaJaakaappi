package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
/**
 * The class with all the methods for accessing the Ostoslista table in the database.
 * @author lauri
 *
 */
public class OstoslistaDAO implements IOstoslistaDAO {
	/**
	 * Holds, manages and provides access to services.
	 */
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	/**
	 * Used for creating database sessions.
	 */
	private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	/**
     * Unit where all the operations happen.
     */
	private static Transaction transaktio = null;
	/**
     * For accessing the Tuote operations.
     */
    private TuoteDAO tuote = new TuoteDAO();
    /**
	 * Inserts a new record in the Ostoslista table.
	 * @param maara The amount of the Tuote being placed in the Ostoslista.
	 * @param tuote_id The ID of the Tuote being placed in the fridge.
	 * @return Returns true if the new record was created successfully into the database.
	 */
	@Override
	public boolean createOstoslista(double maara, int tuote_id) {
		Ostoslista ostoslista = null;
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
			if (istunto.createQuery("select 1 from Ostoslista where tuote_id = :tuoteid")
            		.setParameter("tuoteid", tuote_id)
            		.uniqueResult() != null) {
            	Query query = istunto.createQuery("from Ostoslista where tuote_id = :tuoteid")
            			.setParameter("tuoteid", tuote_id);
    			ostoslista = (Ostoslista) query.uniqueResult();
    			double uusi_maara = ostoslista.getTuote_maara() + maara;
    			query = istunto.createQuery("update Ostoslista set tuote_maara = :maara where tuote_id = :tuoteid")
    					.setParameter("maara", uusi_maara)
    					.setParameter("tuoteid", tuote_id);
                query.executeUpdate();
            	return true;
            } else {
	            Ostoslista uusiOstoslista = new Ostoslista();
	        	uusiOstoslista.setTuote_maara(maara);
	        	uusiOstoslista.setTuote(istunto.get(Tuote.class, tuote_id));
	            istunto.save(uusiOstoslista);
	            istunto.getTransaction().commit();
				return true;
			}
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
            return false;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading a Ostoslista record from the database using the ID and the expiration date of the Tuote to read it.
	 * @param tuote_id The ID of the Tuote in the Ostoslista to be read.
	 * @return Returns the Ostoslista record form the database which was read.
	 */
	@Override
	public Ostoslista readOstoslista(int tuote_id) {
		Ostoslista ostoslista = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Ostoslista where tuote_id = :tuoteid")
					.setParameter("tuoteid", tuote_id);
			ostoslista = (Ostoslista) query.uniqueResult();
			return ostoslista;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading a Ostoslista record from the database, using a ID of a Ostoslista to read it.
	 * @param ostoslista_id The ID of the Ostoslista which is being read.
	 * @return Returns the Ostoslista record from the database with the matching ID.
	 */
	@Override
	public Ostoslista readOstoslistaId(int ostoslista_id) {
		Ostoslista ostoslista = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Ostoslista where ostoslista_id = :ostoslistaid").setParameter("ostoslistaid", ostoslista_id);
			ostoslista = (Ostoslista) query.uniqueResult();
			return ostoslista;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading all the Ostoslista record from the database with the matching Tuote name.
	 * @param tuote_nimi The name of the Tuote associated with the Ostoslista records to be read.
	 * @return Returns an ArrayList with all the Ostoslista records with the specific Tuote name.
	 */
	@Override
	public ArrayList<Object> readOstoslistaName(String tuote_nimi) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();			
			@SuppressWarnings("unchecked")
            List<Object> jkt = istunto.createQuery("from Ostoslista where tuote_id = :tuoteid").setParameter("tuoteid", tuote_id).getResultList();
            for (Object o : jkt) {
                result.add(o);
            }
            return result;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
            return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading all the Ostoslista records from the database.
	 * @return Returns an ArrayList of all the Ostoslista records.
	 */
	@Override
	public ArrayList<Object> readOstoslistat() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_jk = istunto.createQuery("from Ostoslista").getResultList();
            for (Object o : all_jk) {
                result.add(o);
            }
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return result;
	}
	/**
	 * Deleting a Ostoslista record from the database.
	 * @param ostoslista_id The ID of the Ostoslista record to be deleted.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	@Override
	public boolean deleteOstoslista(int ostoslista_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Ostoslista where ostoslista_id = :ostoslistaid").setParameter("ostoslistaid", ostoslista_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Ostoslista where ostoslista_id = :ostoslistaid").setParameter("ostoslistaid", ostoslista_id);
                query.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return false;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Emptying the whole Ostoslista table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	@Override
	public boolean emptyOstoslista() {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete from Ostoslista");
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return false;
        } finally {
            istunto.close();
		}
    }
}