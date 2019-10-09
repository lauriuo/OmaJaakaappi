package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 * The class with all the methods for accessing the Rpk table in the database.
 * @author ville
 *
 */
public class RpkDAO implements IRpkDAO {
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
	 * Inserts a new record into the Rpk table.
	 * @param rpk_pvm The date on which the Rpk record was created.
	 * @param jaakaappi_id The ID of the Jaakaappi record associated with the Rpk record.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	@Override
	public boolean createRpk(Date rpk_pvm, int jaakaappi_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Rpk uusiRpk = new Rpk();
            uusiRpk.setRpk_pvm(rpk_pvm);
            uusiRpk.setJaakaappi(istunto.get(Jaakaappi.class, jaakaappi_id));
            istunto.save(uusiRpk);
            istunto.getTransaction().commit();
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
	 * Reading a Rpk record from the database using the ID of the Rpk record to read it.
	 * @param rpk_id The ID of the Rpk record to be read.
	 * @return Returns the Rpk record which was read.
	 */
	@Override
	public Rpk readRpkId(int rpk_id) {
		Rpk rpk = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Rpk where rpk_id = :rpkid")
					.setParameter("rpkid", rpk_id);
			rpk = (Rpk) query.uniqueResult();
			return rpk;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading a Rpk record from the database using the Jaakaappi record's ID associated with the Rpk.
	 * @param jaakaappi_id The ID of the Jaakaappi record associated with the Rpk record to be read.
	 * @return Return the Rpk record which was read.
	 */
	@Override
	public Rpk readRpkJkId(int jaakaappi_id) {
		Rpk rpk = null;
		Session istunto = istuntotehdas.openSession();
		try {
			Query query = istunto.createQuery("from Rpk where jaakaappi_id = :jaakaappiid")
					.setParameter("jaakaappiid", jaakaappi_id);
			rpk = (Rpk) query.uniqueResult();
			return rpk;
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
    		return null;
        } finally {
            istunto.close();
		}
	}
	/**
	 * Reading all the Rpk record from the database.
	 * @return An ArrayList of all the Rpk records from the database.
	 */
	@Override
	public ArrayList<Object> readRpkt() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> all_rpk = istunto.createQuery("from Rpk").getResultList();
            for (Object o : all_rpk) {
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
	 * Reading all the Rpk records sharing the same date.
	 * @param rpk_pvm The date on which the Rpk records were made.
	 * @return An ArrayList of all the Rpk records matching the date.
	 */
	@Override
	public ArrayList<Object> readRpkPvm(Date rpk_pvm) {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> rpk_by_pvm = istunto.createQuery("from Rpk where rpk_pvm = :rpkpvm").setParameter("rpkpvm", rpk_pvm).getResultList();
            for (Object o : rpk_by_pvm) {
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
	 * Updating a Rpk record in the database.
	 * @param rpk_id The ID of the Rpk record to be updated.
	 * @param rpk_pvm The new date to be set for the Rpk record.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	@Override
	public boolean updateRpk(int rpk_id, Date rpk_pvm) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Rpk set rpk_pvm = :rpkpvm where rpk_id = :rpkid")
            		.setParameter("rpkpvm", rpk_pvm)
            		.setParameter("rpkid", rpk_id);
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
	 * Deleting a Rpk record from the database.
	 * @param rpk_id The ID of the Rpk record to be deleted from the database.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	@Override
	public boolean deleteRpk(int rpk_id) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Rpk where rpk_id = :rpkid").setParameter("rpkid", rpk_id).uniqueResult() != null) {
            	Query query = istunto.createQuery("delete Rpk where rpk_id = :rpkid").setParameter("rpkid", rpk_id);
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
	 * Emptying the whole Rpk table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	@Override
	public boolean emptyRpk() {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete from Rpk");
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
