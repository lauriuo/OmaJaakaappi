package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="aines")
public class Aines {
	@Id
    @Column(name="aines_id")
    private int aines_id;
	
	@Column(name="aines_maara")
    private double aines_maara;
	
	@ManyToOne
    @JoinColumn(name="tuote_id")
    private Tuote tuote;
	
	@ManyToOne
    @JoinColumn(name="resepti_id")
    private Resepti resepti;
	
	public Aines() {
		
	}
	
	public Aines(int aines_id, double aines_maara, Tuote tuote, Resepti resepti) {
		this.aines_id = aines_id;
		this.aines_maara = aines_maara;
		this.tuote = tuote;
		this.resepti = resepti;
	}

	public int getAines_id() {
		return aines_id;
	}

	public void setAines_id(int aines_id) {
		this.aines_id = aines_id;
	}

	public double getAines_maara() {
		return aines_maara;
	}

	public void setAines_maara(double aines_maara) {
		this.aines_maara = aines_maara;
	}

	public Tuote getTuote() {
		return tuote;
	}

	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}

	public Resepti getResepti() {
		return resepti;
	}

	public void setResepti(Resepti resepti) {
		this.resepti = resepti;
	}

	@Override
    public String toString() {
        return "Aines ID: " + aines_id +
                ". Aines määrä: " + aines_maara +
                ". Tuote: " + tuote.getTuote_nimi() +
                ". Resepti: " + resepti.getResepti_nimi();
    }
}
