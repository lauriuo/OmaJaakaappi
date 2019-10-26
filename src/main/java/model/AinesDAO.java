package model;

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
 * The class with all the methods for accessing the Aines table in the database.
 * @author ville
 *
 */
public class AinesDAO implements IAinesDAO {
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
     * For accessing the Resepti operations.
     */
    private ReseptiDAO resepti = new ReseptiDAO();
    
    /**
	 * Inserts a new record into the Aines table.
	 * @param tuote_id The ID of a Tuote associated with the Aines.
	 * @param resepti_id The ID of a Resepti associated with the Aines.
	 * @param maara The amount of associated Tuote of the Aines.
	 * @return Returns true if creation was successful, false in other cases.
	 */
	@Override
	public boolean createAines(int tuote_id, int resepti_id, double maara) {
		Aines aines = null;
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Aines where tuote_id = :tuoteid and resepti_id = :reseptiid")
            		.setParameter("tuoteid", tuote_id)
            		.setParameter("reseptiid", resepti_id)
            		.uniqueResult() != null) {
            	Query query = istunto.createQuery("from Aines where tuote_id = :tuoteid and resepti_id = :reseptiid")
            			.setParameter("tuoteid", tuote_id)
            			.setParameter("reseptiid", resepti_id);
    			aines = (Aines) query.uniqueResult();
    			double uusi_maara = aines.getAines_maara() + maara;
    			query = istunto.createQuery("update Aines set aines_maara = :maara where tuote_id = :tuoteid and resepti_id = :reseptiid")
    					.setParameter("maara", uusi_maara)
    					.setParameter("tuoteid", tuote_id)
                		.setParameter("reseptiid", resepti_id);
                query.executeUpdate();
            	return true;
            } else {
	            Aines uusiAines = new Aines();
	            uusiAines.setTuote(istunto.get(Tuote.class, tuote_id));
	            uusiAines.setResepti(istunto.get(Resepti.class, resepti_id));
	            uusiAines.setAines_maara(maara);
	        	istunto.save(uusiAines);
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
	 * Reads a specific Aines record from the database, using the associated Tuote and Resepti to define which Aines record.
	 * @param tuote_nimi The name of the Tuote associated with the Aines to be read.
	 * @param resepti_nimi The name of the Resepti associated with the Aines to be read.
	 * @return Returns the Aines record from the database.
	 */
	@Override
	public Aines readAines(String tuote_nimi, String resepti_nimi) {
		Aines aines = null;
		Session istunto = istuntotehdas.openSession();
		try {
			int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();
			int resepti_id = resepti.readReseptiNimi(resepti_nimi).getResepti_id();
			Query query = istunto.createQuery("from Aines where tuote_id = :tuoteid and resepti_id = :reseptiid")
					.setParameter("tuoteid", tuote_id)
					.setParameter("reseptiid", resepti_id);
			aines = (Aines) query.uniqueResult();
			return aines;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reads specific Aines recordfrom the database, using the ID to determinate which one.
	 * @param aines_id The ID of the Aines which record is wanted.
	 * @return The Aines which was read.
	 */
	@Override
	public Aines readAinesId(int aines_id) {
		Aines aines = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Aines where aines_id = :ainesid")
					.setParameter("ainesid", aines_id);
			aines = (Aines) query.uniqueResult();
			return aines;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading all the Aines records in the database.
	 * @return Arraylist of all the Aines records in the database.
	 */
	@Override
	public ArrayList<Object> readAinekset() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_ainekset = istunto.createQuery("from Aines").getResultList();
            for (Object o : all_ainekset) {
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
	 * Reading all the Aines records from the database that are associated with a specific Resepti's ID.
	 * @param resepti_id The ID of the Resepti which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records which are associated with the resepti_id.
	 */
	@Override
	public ArrayList<Object> readAineksetReseptiID(int resepti_id) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {			
			@SuppressWarnings("unchecked")
            List<Object> ainekset = istunto.createQuery("from Aines where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id).getResultList();
            for (Object o : ainekset) {
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
	 * Reading all the Aines records from the database which are associated with a specific Resepti's name.
	 * @param resepti_nimi The name of the Resepti which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records with are associated with the name of the Resepti.
	 */
	@Override
	public ArrayList<Object> readAineksetReseptiNimi(String resepti_nimi) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			int resepti_id = resepti.readReseptiNimi(resepti_nimi).getResepti_id();			
			@SuppressWarnings("unchecked")
            List<Object> ainekset = istunto.createQuery("from Aines where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id).getResultList();
            for (Object o : ainekset) {
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
	 * Reading all the Aines records from the database that are associated with specific Tuote's name.
	 * @param tuote_nimi The name of the Tuote which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records that are associated with the name of the Tuote.
	 */
	@Override
	public ArrayList<Object> readAinesReseptit(String tuote_nimi) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			int tuote_id = tuote.readTuoteNimi(tuote_nimi).getTuote_id();			
			@SuppressWarnings("unchecked")
            List<Object> reseptit = istunto.createQuery("select rese.resepti_nimi from Resepti rese inner join Aines aine on rese.resepti_id = aine.resepti where aine.tuote = " + tuote_id + "").getResultList();
            for (Object o : reseptit) {
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
	 * Updating a record in the Aines table of the database.
	 * @param aines_id The ID of the Aines to be updated.
	 * @param uusi_tuote The name of the new Tuote to be associated with the Aines.
	 * @param uusi_resepti The name of the new Resepti to be associated with the Aines.
	 * @param uusi_maara The new amount of associated Tuote used.
	 * @return Returns true if the update was successful. Returns false in other cases.
	 */
	@Override
	public boolean updateAines(int aines_id, String uusi_tuote, String uusi_resepti, double uusi_maara) {
		Aines aines = null;
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
			int tuote_id = tuote.readTuoteNimi(uusi_tuote).getTuote_id();
			int resepti_id = resepti.readReseptiNimi(uusi_resepti).getResepti_id();
			Query query = istunto.createQuery("update Aines set aines_maara = :uusimaara , tuote_id = :tuoteid , resepti_id = :reseptiid "
					+ "where aines_id = :ainesid")
            		.setParameter("uusimaara", uusi_maara)
            		.setParameter("tuoteid", tuote_id)
            		.setParameter("reseptiid", resepti_id)
            		.setParameter("ainesid", aines_id);
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
	 * Deleting a record from the Aines table in the database.
	 * @param aines_id The ID of the Aines to be removed from the table.
	 * @return Return true if the record was deleted from the table successfully. Returns false in other cases.
	 */
	@Override
	public boolean deleteAines(int aines_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Aines where aines_id = :ainesid").setParameter("ainesid", aines_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Aines where aines_id = :ainesid").setParameter("ainesid", aines_id);
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
	 * Dleting all the Aines records from the table which are associated with specific Resepti.
	 * @param resepti_id The ID of the Resepti which associated Aines records will be deleted.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	@Override
	public boolean deleteAineksetResepti(int resepti_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Aines where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Aines where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id);
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
	 * Emptying the whole Aines table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	@Override
	public boolean emptyAines() {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete from Aines");
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
	 * For a given recipe, searches the ingredients for it that are in the Jaakaappi table.
	 * @return Returns an ArrayList of Aines objects if the operation was successful. Returns null otherwise.
	 */
	@Override
	public ArrayList<Object> availableForResepti(int resepti_id) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			if (resepti.readReseptiId(resepti_id) != null) {
				@SuppressWarnings("unchecked")
				List<Aines> aines = istunto.createQuery("select DISTINCT aines from Aines aines " +
												 "inner join Jaakaappi jaakaappi on jaakaappi.tuote = aines.tuote " +
												 "inner join Resepti resepti on aines.resepti = resepti.resepti_id " +
												 "where resepti.resepti_id = " + resepti_id)
												 .getResultList();
				for (Object o : aines) {
					result.add(o);	
				}
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
            return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * For a given recipe, searches the ingredients for it that are not in the Jaakaappi table.
	 * @return Returns an ArrayList of Aines objects if the operation was successful. Returns null otherwise.
	 */
	@Override
	public ArrayList<Object> notAvailableForResepti(int resepti_id) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			if (resepti.readReseptiId(resepti_id) != null) {
				@SuppressWarnings("unchecked")
				List<Aines> aines = istunto.createQuery("select aines from Aines aines " +
												 "inner join Resepti resepti on aines.resepti = resepti.resepti_id " +
												 "where resepti.resepti_id = " + resepti_id + 
												 " and aines.aines_id not in " +
												 "(select aines.aines_id from Aines aines inner join " + 
												 "Jaakaappi jaakaappi on jaakaappi.tuote = aines.tuote)")
												 .getResultList();
				for (Object o : aines) {
					result.add(o);	
				}
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
            return null;
        } finally {
            istunto.close();
		}
	}
}
