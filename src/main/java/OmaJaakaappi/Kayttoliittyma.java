package OmaJaakaappi;

import java.util.ArrayList;

public class Kayttoliittyma {
        public static void main(String[] args) {
            HibernateHaku haku = new HibernateHaku();

            //Tuote t = new Tuote(2, "kalja", "litra", 2000);
            //haku.Lisays(t);
            //haku.Poisto("delete Tuote where tuote_nimi = 'kalja'");
            //esimerkkejä haku / lisays / poisto käytöstä.

            ArrayList<Object> haut = haku.Haku("from Tuote");
            for (Object h : haut) {
                System.out.println(h.toString());
            }


            // while (valinta != "Q") {
            // kyselylooppi tähän
            // }
        }
    }
