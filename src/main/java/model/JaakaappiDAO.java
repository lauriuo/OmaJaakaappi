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

public class JaakaappiDAO implements IJaakaappiDAO {
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static Transaction transaktio = null;
    private TuoteDAO tuote = new TuoteDAO();

	@Override
	public boolean createJaakaappi(Date pvm, double maara, String status, int tuote_id) {
		Jaakaappi jaakaappi = null;
		Session istunto = istuntotehdas.openSession();
		try {
            transaktio = istunto.beginTransaction();
			/*  if (istunto.createQuery("select 1 from Jaakaappi where tuote_id = :tuoteid and tuote_pvm = :pvm")
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
            } else */
	            Jaakaappi uusiJaakaappi = new Jaakaappi();
	        	uusiJaakaappi.setTuote_pvm(pvm);
	        	uusiJaakaappi.setTuote_maara(maara);
	        	uusiJaakaappi.setTuote_status(status);
	        	uusiJaakaappi.setTuote(istunto.get(Tuote.class, tuote_id));
	            istunto.save(uusiJaakaappi);
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
