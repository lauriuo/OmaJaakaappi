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
 * The class with all the methods for accessing the Resepti table in the database.
 * @author ville
 *
 */
public class ReseptiDAO implements IReseptiDAO {
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
	 * Creating a new record in the Resepti table.
	 * @param resepti_nimi The name for the Resepti.
	 * @param resepti_ohje The recipe for the Resepti.
	 * @return Returns true if the insert was successful. Returns false in other cases.
	 */
	@Override
	public boolean createResepti(String resepti_nimi, String resepti_ohje) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Resepti where resepti_nimi = :reseptinimi").setParameter("reseptinimi", resepti_nimi).uniqueResult() != null) {
            	return false;
            } else {
            	Resepti uusiResepti = new Resepti();
            	uusiResepti.setResepti_nimi(resepti_nimi);
            	uusiResepti.setResepti_ohje(resepti_ohje);
                istunto.save(uusiResepti);
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
	 * Reading a Resepti from the database, using a name of a Resepti to read it.
	 * @param resepti_nimi The name of the Resepti which is being read.
	 * @return Returns a Resepti from the database with the matching name.
	 */
	@Override
	public Resepti readReseptiNimi(String resepti_nimi) {
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Resepti where resepti_nimi = :reseptinimi").setParameter("reseptinimi", resepti_nimi);
			Resepti resepti = (Resepti) query.uniqueResult();
			return resepti;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
            return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading a Resepti from the database, using a ID of a Resepti to read it. 
	 * @param resepti_id The ID of the Resepti which is being read.
	 * @return Returns a Resepti from the database with the matching ID.
	 */
	@Override
	public Resepti readReseptiId(int resepti_id) {
		Resepti resepti = null;
		Session istunto = istuntotehdas.openSession();
		try {
			resepti = istunto.get(Resepti.class, resepti_id);
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return resepti;
	}
	/**
	 * Reading all the Resepti records from the database.
	 * @return ArrayList with all the Resepti records from the database.
	 */
	@Override
	public ArrayList<Object> readReseptit() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> haut = istunto.createQuery("from Resepti").getResultList();
            for (Object o : haut) {
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
	 * Updating a Resepti record in the database.
	 * @param resepti_id The ID of the Resepti to be updated.
	 * @param uusi_nimi The new name for the Resepti which is being updated.
	 * @param uusi_ohje The new recipe for the Resepti which is being updated.
	 * @return Returns true if the update was successful. Returns false in other cases.
	 */
	@Override
	public boolean updateResepti(int resepti_id, String uusi_nimi, String uusi_ohje) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Resepti set resepti_nimi = :reseptinimi , resepti_ohje = :reseptiohje where resepti_id = :reseptiid")
            		.setParameter("reseptinimi", uusi_nimi)
            		.setParameter("reseptiohje", uusi_ohje)
            		.setParameter("reseptiid", resepti_id);
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
	 * Deleting a Resepti record from the database.
	 * @param resepti_id The ID of the Resepti which is being removed.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	@Override
	public boolean deleteResepti(int resepti_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Resepti where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Resepti where resepti_id = :reseptiid").setParameter("reseptiid", resepti_id);
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
	 * Emptying the whole Resepti table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	@Override
	public boolean emptyResepti() {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete from Resepti");
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
