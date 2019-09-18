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

public class TuoteDAO implements ITuoteDAO {
	
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static Transaction transaktio = null;

	@Override
	public boolean createTuote(String nimi, String yksikko, int kcal) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            if (istunto.createQuery("select 1 from Tuote where tuote_nimi = :tuotenimi").setParameter("tuotenimi", nimi).uniqueResult() != null) {
            	return false;
            } else {
            	Tuote uusiTuote = new Tuote();
                uusiTuote.setTuote_nimi(nimi);
                uusiTuote.setTuote_yksikko(yksikko);
                uusiTuote.setTuote_kcal(kcal);
                istunto.save(uusiTuote);
                istunto.getTransaction().commit();
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return true;
	}

	@Override
	public Tuote readTuote(int tuote_id) {
		Tuote tuote = null;
		Session istunto = istuntotehdas.openSession();
		try {
			tuote = istunto.get(Tuote.class, tuote_id);
			istunto.close();
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return tuote;
	}

	@Override
	public ArrayList<Object> readTuotteet() {
		ArrayList<Object> result = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try {
			@SuppressWarnings("unchecked")
            List<Object> haut = istunto.createQuery("from Tuote").getResultList();
            for (Object o : haut) {
                result.add(o);
            }
            istunto.close();
		} catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return result;
	}

	@Override
	public boolean updateTuote(String vanha_nimi, String uusi_nimi, String uusi_yksikko, int uusi_kcal) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("update Tuote set tuote_nimi = :nimi , tuote_yksikko = :yksikko ,"
            		+ " tuote_kcal = :kcal where tuote_nimi = :vanha_nimi")
            		.setParameter("nimi", uusi_nimi)
            		.setParameter("yksikko", uusi_yksikko)
            		.setParameter("kcal", uusi_kcal)
            		.setParameter("vanha_nimi", vanha_nimi);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return false;
	}

	@Override
	public boolean deleteTuote(String tuote_nimi) {
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
            Query query = istunto.createQuery("delete Tuote where tuote_nimi = :nimi").setParameter("nimi", tuote_nimi);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return false;
	}

}
