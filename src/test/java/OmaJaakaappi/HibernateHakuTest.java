package OmaJaakaappi;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateHakuTest {
    public static void main( String[] args ) {
        StandardServiceRegistry registry =
        new StandardServiceRegistryBuilder().configure()
                                            .build();
        SessionFactory istuntotehdas =
        new MetadataSources(registry).buildMetadata()
                                     .buildSessionFactory();
        
        Transaction transaktio = null;
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Tuote> result = istunto.createQuery("from Tuote")
                                        .getResultList();
            for (Tuote t : result) {
                System.out.println(t.toString());
            }
        } catch (Exception e) {
            if (transaktio != null) transaktio.rollback();
            e.printStackTrace();
        }
        istuntotehdas.close();
    }
}