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

public class ReseptiDAO implements IReseptiDAO {
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static Transaction transaktio = null;

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
	@Override
	public double countKcalResepti(int resepti_id) {
		Session istunto = istuntotehdas.openSession();
		double result = 0;
		try {
            transaktio = istunto.beginTransaction();
			if (readReseptiId(resepti_id) != null) {
				Query q = istunto.createQuery("select tuote.tuote_kcal, aines.aines_maara from Tuote tuote " + 
															"inner join Aines aines on aines.tuote = tuote.tuote_id " +
															"inner join Resepti resepti on aines.resepti = resepti.resepti_id " +
															"where resepti.resepti_id = " + resepti_id);
				List<Object[]> kcal_and_maara = (List<Object[]>) q.list();

				for (Object[] o : kcal_and_maara) {
					double kcal = (double) o[0];
					double maara = (double) o[1];
					result += kcal * maara;
				}
				return result;
			}
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        } finally {
            istunto.close();
		}
		return 0;
	}
}
