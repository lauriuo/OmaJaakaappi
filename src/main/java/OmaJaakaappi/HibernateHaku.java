package OmaJaakaappi;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import model.Jaakaappi;
import model.Tuote;

public class HibernateHaku {

        public HibernateHaku() {
        }

        public ArrayList<Object> Haku(String s) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Transaction transaktio = null;
            ArrayList<Object> result = new ArrayList<>();

            try (Session istunto = istuntotehdas.openSession()) {
                transaktio = istunto.beginTransaction();
                @SuppressWarnings("unchecked")
                List<Object> haut = istunto.createQuery(s).getResultList();
                for (Object o : haut) {
                    result.add(o);
                }
            } catch (Exception e) {
                if (transaktio != null) transaktio.rollback();
                e.printStackTrace();
            }
            return result;
        }
        public boolean Lisays(Object o) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Transaction transaktio = null;
            try (Session istunto = istuntotehdas.openSession()) {
                transaktio = istunto.beginTransaction();
                istunto.save(o);
                istunto.getTransaction().commit();
                return true;
            } catch (Exception e) {
                if (transaktio != null) transaktio.rollback();
                e.printStackTrace();
            }
            return false;
        }
        public boolean Poisto(String s) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Transaction transaktio = null;
            try (Session istunto = istuntotehdas.openSession()) {
                transaktio = istunto.beginTransaction();
                Query query = istunto.createQuery(s);
                query.executeUpdate();
                return true;
            } catch (Exception e) {
                if (transaktio != null) transaktio.rollback();
                e.printStackTrace();
            }
            return false;
        }
    public static void main( String[] args ) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Transaction transaktio = null;
        
        ////kaikki tuotteet
        
      //yksittäisen tuotteen etsiminen syötetyn nimen mukaan
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            String nimi = "Vettä";
            List<Tuote> result = istunto.createQuery("from Tuote where tuote_nimi = :tuotenimi").setParameter("tuotenimi", nimi).getResultList();
            for (Tuote t : result) {
                System.out.println(t.toString());
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        
        //tarkistetaan löytyykö tuote nimen perusteella, lisätään jos ei löydy
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            String nimi = "Maito";
            if (istunto.createQuery("select 1 from Tuote where tuote_nimi = :tuotenimi").setParameter("tuotenimi", nimi).uniqueResult() != null) {
            	System.out.println("Tuote on jo olemassa. Uutta tuotetta ei lisätty.");
            } else {
            	System.out.println("Tuotetta ei löytynyt. Lisätään uusi tuote.");
            	Tuote uusiTuote = new Tuote();
                uusiTuote.setTuote_nimi("Maito");
                uusiTuote.setTuote_yksikko("litra");
                uusiTuote.setTuote_kcal(33);
                istunto.save(uusiTuote);
                istunto.getTransaction().commit();
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        /**
        //poistetaan tuote
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            String nimi = "Ruislimppu";
            Query query = istunto.createQuery("delete Tuote where tuote_nimi = :tuotenimi").setParameter("tuotenimi", nimi);
            query.executeUpdate();
            System.out.println("Tuote poistettu.");
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        **/
        
      //kaikki jääkaapissa olevat tuotteet
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Jaakaappi> result = istunto.createQuery("from Jaakaappi").getResultList();
            for (Jaakaappi jk : result) {
                System.out.println(jk.toString());
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        
      //tuotteet päivämäärän mukaan
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            String pvm = "2019-9-20";
            List<Jaakaappi> result = istunto.createQuery("from Jaakaappi where tuote_pvm = '" + pvm + "'").getResultList(); //setParameter ei toiminut jostain syystä
            for (Jaakaappi jk : result) {
                System.out.println(jk.toString());
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        
        //lisäys jääkaappiin
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            Jaakaappi uusiJk = new Jaakaappi();
            uusiJk.setTuote_pvm(new Date(2019-9-21));
            uusiJk.setTuote_maara(8);
            uusiJk.setTuote_status("Käytettävissä");
            Tuote ttt = new Tuote(3, "Maito", "litra", 33);
            uusiJk.setTuote(ttt);
            istunto.save(uusiJk);
            istunto.getTransaction().commit();
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        
        //päivitetään jääkaapin tuote
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            String status = "Vanhentunut";
            Query query = istunto.createQuery("update Jaakaappi set tuote_status = :status where tuote_pvm = '1970-01-01'").setParameter("status", status);
            query.executeUpdate();
            System.out.println("Status päivitetty.");
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }	
        istuntotehdas.close();
    }
}