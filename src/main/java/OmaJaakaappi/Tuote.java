package OmaJaakaappi;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="tuote")
public class Tuote {
    @Id
    @Column(name="tuote_id")
    private int tuote_id;

    @Column(name="tuote_nimi")
    private String tuote_nimi;

    @Column(name="tuote_yksikko")
    private String tuote_yksikko;

    @Column(name="tuote_kcal")
    private int tuote_kcal;
    
    public Tuote () {
    }

    public Tuote (int tuote_id, String tuote_nimi,
     String tuote_yksikko, int tuote_kcal) {
        this.tuote_id = tuote_id;
        this.tuote_kcal = tuote_kcal;
        this.tuote_yksikko = tuote_yksikko;
        this.tuote_nimi = tuote_nimi;
    }
    public int getTuote_id() {
        return tuote_id;
    }
    
    public int getTuote_kcal() {
        return tuote_kcal;
    }
    public void setTuote_kcal(int kcal) {
    	this.tuote_kcal = kcal;
    }
    
    public String getTuote_nimi() {
        return tuote_nimi;
    }
    public void setTuote_nimi(String nimi) {
    	this.tuote_nimi = nimi;
    }
    
    public String getTuote_yksikko() {
        return tuote_yksikko;
    }
    public void setTuote_yksikko(String yksikko) {
    	this.tuote_yksikko = yksikko;
    }
    
    @Override
    public String toString() {
        return "id: " + tuote_id +
                " nimi: " + tuote_nimi +
                " kcal: " + tuote_kcal +
                " yksikkö: " + tuote_yksikko;
    }
}