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

public class AinesDAO implements IAinesDAO {
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static Transaction transaktio = null;
    private TuoteDAO tuote = new TuoteDAO();
    private ReseptiDAO resepti = new ReseptiDAO();

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

}
