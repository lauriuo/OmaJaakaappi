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

public class RpkDAO implements IRpkDAO {
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static Transaction transaktio = null;

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
