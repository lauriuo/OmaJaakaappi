package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Handles the Aines objects created, corresponding the Aines table in the database.
 * @author ville
 *
 */
@Entity
@Table(name="aines")
public class Aines {
	/**
	 * A primary key column in the Aines table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
	@Id
    @Column(name="aines_id")
    private int aines_id;
	/**
	 * A column in the Aines table. Present the amount of a associated Tuote. Double data type. 
	 */
	@Column(name="aines_maara")
    private double aines_maara;
	/**
	 * A foreign key column in the Aines table. Identification for a Tuote associated with the Aines. Tuote object data type.
	 */
	@ManyToOne
    @JoinColumn(name="tuote_id")
    private Tuote tuote;
	/**
	 * A foreign key column in the Aines table. Identification for a Resepti associated with the Aines. Resepti object data type.
	 */
	@ManyToOne
    @JoinColumn(name="resepti_id")
    private Resepti resepti;
	/**
	 * Initializes an empty Aines object.
	 */
	public Aines() {
		
	}
	/**
	 * Initializes an Aines object.
	 * @param aines_id The identification number for the Aines table's aine_id column, integer data type.
	 * @param aines_maara The amount of this Aines for the Aines table's aine_maara column, double data type.
	 * @param tuote The Tuote object this Aines is associated with for the Aines table's tuote_id table, Tuote object data type.
	 * @param resepti The Resepti object this Aines is associated with for the Aines table's resepti_id table, Resepti object data type.
	 */
	public Aines(int aines_id, double aines_maara, Tuote tuote, Resepti resepti) {
		this.aines_id = aines_id;
		this.aines_maara = aines_maara;
		this.tuote = tuote;
		this.resepti = resepti;
	}
	/**
	 * Returns the value of aines_id set for a Aines object.
	 * @return Integer value of Aines object's aines_id.
	 */
	public int getAines_id() {
		return aines_id;
	}
	/**
	 * Sets the aines_id of an Aines object.
	 * @param aines_id The value which an Aines object's aines_id is to be set, integer data type.
	 */
	public void setAines_id(int aines_id) {
		this.aines_id = aines_id;
	}
	/**
	 * Returns the value of aines_maara set for an Aines object.
	 * @return Double value of a Aines object's aines_maara.
	 */
	public double getAines_maara() {
		return aines_maara;
	}
	/**
	 * Sets the aines_maara of an Aines object.
	 * @param aines_maara The value which Aines object's aines_maara is to be set, double data type.
	 */
	public void setAines_maara(double aines_maara) {
		this.aines_maara = aines_maara;
	}
	/**
	 * Returns the Tuote object which is associated with the Aines.
	 * @return Tuote object associated with the Aines.
	 */
	public Tuote getTuote() {
		return tuote;
	}
	/**
	 * Sets the Tuote object associated with the Aines.
	 * @param tuote The Tuote object to be associated with the Aines, Tuote object data type.
	 */
	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
	/**
	 * Returns the Resepti object which is associated with the Aines.
	 * @return resepti object associated with the Aines.
	 */
	public Resepti getResepti() {
		return resepti;
	}
	/**
	 * Sets the Resepti object associated with the Aines.
	 * @param resepti The Resepti object to be associated with the Aines, Resepti object data type.
	 */
	public void setResepti(Resepti resepti) {
		this.resepti = resepti;
	}
	/**
	 * Generates the values of Aines object into a string.
	 */
	@Override
    public String toString() {
        return "Aines ID: " + aines_id +
                ". Aines määrä: " + aines_maara +
                ". Tuote: " + tuote.getTuote_nimi() +
                ". Resepti: " + resepti.getResepti_nimi();
    }
}
