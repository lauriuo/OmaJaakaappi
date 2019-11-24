package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
/**
 * The class with all the methods for accessing the Jaakaappi table in the database.
 * @author ville
 *
 */
public class JaakaappiDAO implements IJaakaappiDAO {
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
     * For accessing the Rpk operations.
     */
    private RpkDAO rpk = new RpkDAO();
    /**
	 * Inserts a new record in the Jaakaappi table.
	 * @param pvm The expiration date of the Tuote being placed in the Jaakaappi.
	 * @param maara The amount of the Tuote being placed in the Jaakaappi.
	 * @param status The usability status of the Tuote being placed in the Jaakaappi.
	 * @param tuote_id The ID of the Tuote being placed in the fridge.
	 * @return Returns true if the new record was created successfully into the database.
	 */
	@Override
	public boolean createJaakaappi(Date pvm, double maara, String status, int tuote_id) {
		Jaakaappi jaakaappi = null;
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
			if (istunto.createQuery("select 1 from Jaakaappi where tuote_id = :tuoteid and tuote_pvm = :pvm")
            		.setParameter("tuoteid", tuote_id)
            		.setParameter("pvm", pvm)
            		.uniqueResult() != null) {
            	Query query = istunto.createQuery("from Jaakaappi where tuote_id = :tuoteid and tuote_pvm = :pvm")
            			.setParameter("tuoteid", tuote_id)
            			.setParameter("pvm", pvm);
    			jaakaappi = (Jaakaappi) query.uniqueResult();
    			double uusi_maara = jaakaappi.getTuote_maara() + maara;
    			query = istunto.createQuery("update Jaakaappi set tuote_maara = :maara where tuote_id = :tuoteid and tuote_pvm = :pvm")
    					.setParameter("maara", uusi_maara)
    					.setParameter("tuoteid", tuote_id)
                		.setParameter("pvm", pvm);
                query.executeUpdate();
            	return true;
            } else {
	            Jaakaappi uusiJaakaappi = new Jaakaappi();
	        	uusiJaakaappi.setTuote_pvm(pvm);
	        	uusiJaakaappi.setTuote_maara(maara);
	        	uusiJaakaappi.setTuote_status(status);
	        	uusiJaakaappi.setTuote(istunto.get(Tuote.class, tuote_id));
	            istunto.save(uusiJaakaappi);
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
	 * Reading a Jaakaappi record from the database using the ID and the expiration date of the Tuote to read it.
	 * @param tuote_id The ID of the Tuote in the Jaakaappi to be read.
	 * @param pvm The expiration date of the Tuote which is being read from the Jaakaappi.
	 * @return Returns the Jaakaappi record form the database which was read.
	 */
	@Override
	public Jaakaappi readJaakaappi(int tuote_id, Date pvm) {
		Jaakaappi jaakaappi = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Jaakaappi where tuote_id = :tuoteid and tuote_pvm = :pvm order by tuote_pvm")
					.setParameter("tuoteid", tuote_id)
					.setParameter("pvm", pvm);
			jaakaappi = (Jaakaappi) query.uniqueResult();
			return jaakaappi;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading a Jaakaappi record from the database, using a ID of a Jaakaappi to read it.
	 * @param jaakaappi_id The ID of the Jaakaappi which is being read.
	 * @return Returns the Jaakaappi record from the database with the matching ID.
	 */
	@Override
	public Jaakaappi readJaakaappiId(int jaakaappi_id) {
		Jaakaappi jaakaappi = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Jaakaappi where jaakaappi_id = :jaakaappiid").setParameter("jaakaappiid", jaakaappi_id);
			jaakaappi = (Jaakaappi) query.uniqueResult();
			return jaakaappi;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading all the Jaakaappi record from the database with the matching Tuote name.
	 * @param tuote_nimi The name of the Tuote associated with the Jaakaappi records to be read.
	 * @return Returns an ArrayList with all the Jaakaappi records with the specific Tuote name.
	 */
	@Override
	public ArrayList<Object> readJaakaappiNimi(String tuote_nimi) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();			
			@SuppressWarnings("unchecked")
            List<Object> jkt = istunto.createQuery("from Jaakaappi where tuote_id = :tuoteid").setParameter("tuoteid", tuote_id).getResultList();
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
	 * Reading all the Jaakaappi records from the database.
	 * @return Returns an ArrayList of all the Jaakaappi records.
	 */
	@Override
	public ArrayList<Object> readJaakaapit() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_jk = istunto.createQuery("from Jaakaappi where tuote_status = 'Käytettävissä' order by tuote_pvm desc").getResultList();
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
	 * Reading all the Jaakaappi records from the database with the status set as "Käytetty".
	 * @return An ArrayList of all the Jaakaappi records with the status "Käytetty.
	 */
	@Override
	public ArrayList<Object> readUsedJaakaapit() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_jk = istunto.createQuery("from Jaakaappi where tuote_status = 'Käytetty' order by tuote_pvm desc").getResultList();
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
	 * Reading all the Jaakaappi records from the database with the status set as "Hävikki".
	 * @return An ArrayList of all the Jaakaappi records with the status "Hävikki.
	 */
	@Override
	public ArrayList<Object> readWasteJaakaapit() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_jk = istunto.createQuery("from Jaakaappi where tuote_status = 'Hävikki' order by tuote_pvm desc").getResultList();
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
	 * Reading all the Jaakaappi records from the database with the expiration date being in the next two days or less.
	 * @return An ArrayList of Jaakaappi records with the expritaion dates being two days or less away.
	 */
	@Override
	public ArrayList<Object> readGoingOldJaakaapit() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus2days = LocalDateTime.now().plusDays(2);
		java.sql.Date sqltimenow = java.sql.Date.valueOf(now.toLocalDate());
		java.sql.Date sqltime2days = java.sql.Date.valueOf(plus2days.toLocalDate());

		try {
			@SuppressWarnings("unchecked")
			List<Object> all_jk = istunto.createQuery("from Jaakaappi where tuote_status = 'Käytettävissä' " +
				"and tuote_pvm >= :sqltimenow and tuote_pvm <= :sqltime2days order by tuote_pvm desc")
				.setParameter("sqltime2days", sqltime2days)
				.setParameter("sqltimenow", sqltimenow)
				.getResultList();

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
	 * Updating a Jaakaappi record in the database.
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @param uusi_pvm The new expiration date to be set for the Jaakaappi record.
	 * @param uusi_maara The new amount of the Tuote in the Jaakaappi record.
	 * @param uusi_status The new status for the Tuote in the Jaakaappi record.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	@Override
	public boolean updateJaakaappi(int jaakaappi_id, Date uusi_pvm, double uusi_maara, String uusi_status) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Jaakaappi set tuote_pvm = :pvm , tuote_maara = :maara ,"
            		+ " tuote_status = :status where jaakaappi_id = :jaakaappiid")
            		.setParameter("pvm", uusi_pvm)
            		.setParameter("maara", uusi_maara)
            		.setParameter("status", uusi_status)
            		.setParameter("jaakaappiid", jaakaappi_id);
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
	/**
	 * Udating the status of a Jaakaappi record to "Käytetty".
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	@Override
	public boolean updateJkKaytetty(int jaakaappi_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Jaakaappi set tuote_status = 'Käytetty' where jaakaappi_id = :jaakaappiid")
            		.setParameter("jaakaappiid", jaakaappi_id);
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
	/**
	 * Updating the status of a Jaakaappi record to "Hävikki".
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	@Override
	public boolean updateJkHavikki(int jaakaappi_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Jaakaappi set tuote_status = 'Hävikki' where jaakaappi_id = :jaakaappiid")
            		.setParameter("jaakaappiid", jaakaappi_id);
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
	/**
	 * Deleting a Jaakaappi record from the database.
	 * @param jaakaappi_id The ID of the Jaakaappi record to be deleted.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	@Override
	public boolean deleteJaakaappi(int jaakaappi_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Jaakaappi where jaakaappi_id = :jaakaappiid").setParameter("jaakaappiid", jaakaappi_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Jaakaappi where jaakaappi_id = :jaakaappiid").setParameter("jaakaappiid", jaakaappi_id);
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
	 * Splitting a Jaakaappi record into two new ones with the different amount of Tuote.
	 * @param jaakaappi_id The ID of the Jaakaappi to be split.
	 * @param maara The amount of the Tuote in the original Jaakaappi record to be put into a new record.
	 * @param kaytetty_tai_havikki The status for the new Jaakaappi record being made with the splitting.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	@Override
	public boolean updateJkMaara(int jaakaappi_id, double maara, String kaytetty_tai_havikki) {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			Query query = istunto.createQuery("from Jaakaappi where jaakaappi_id = :jaakaappiid").setParameter("jaakaappiid", jaakaappi_id);
			Jaakaappi jaakaappi = (Jaakaappi) query.uniqueResult();
			double erotus = jaakaappi.getTuote_maara() - maara;
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			if (erotus <= 0) {
				updateJaakaappi(jaakaappi_id, jaakaappi.getTuote_pvm(),
								jaakaappi.getTuote_maara(), kaytetty_tai_havikki);
				rpk.createRpk(date, jaakaappi_id);
				
			} else {
					updateJaakaappi(jaakaappi_id, jaakaappi.getTuote_pvm(),
									erotus, jaakaappi.getTuote_status());
		            Jaakaappi uusiJaakaappi = new Jaakaappi();
	        		uusiJaakaappi.setTuote_pvm(jaakaappi.getTuote_pvm());
	        		uusiJaakaappi.setTuote_maara(maara);
	        		uusiJaakaappi.setTuote_status(kaytetty_tai_havikki);
	        		uusiJaakaappi.setTuote(istunto.get(Tuote.class, jaakaappi.getTuote().getTuote_id()));
	            	istunto.save(uusiJaakaappi);
	            	istunto.getTransaction().commit();
	            	
	            	query = istunto.createQuery("select max(jaakaappi_id) from Jaakaappi");
	    			int luku = (Integer) query.getSingleResult();
	    			rpk.createRpk(date, luku);
				}
			return true;
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return false;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Emptying the whole Jaakaappi table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	@Override
	public boolean emptyJaakaappi() {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete from Jaakaappi");
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
