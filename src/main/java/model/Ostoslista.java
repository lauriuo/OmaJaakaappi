package model;

import javax.persistence.*;
/**
 * Handles the Ostoslista objects created, corresponding the Ostoslista table in the database.
 * @author lauri
 *
 */
@Entity
@Table(name="ostoslista")
public class Ostoslista {
	/**
	 * A primary key column in the Ostoslista table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
	@Id
    @Column(name="ostoslista_id")
    private int ostoslista_id;
    /**
	 * A column in the Ostoslista table. Present the amount of an associated Tuote. Double data type. 
	 */
    @Column(name="tuote_maara")
    private double tuote_maara;
    /**
	 * A foreign key column in the Ostoslista table. Identification for a Tuote associated with the Ostoslista. Tuote object data type.
	 */
    @ManyToOne
    @JoinColumn(name="tuote_id")
    private Tuote tuote;
        /**
	 * Initializes an empty Ostoslista object.
	 */
    public Ostoslista() {	
    
    }
    /**
     * Initializes a Ostoslista object.
     * @param ostoslista_id The identification number for the Ostoslista table's ostoslista_id column, integer data type.
     * @param tuote_maara The amount of the associated Tuote with this Ostoslista for the Ostoslista table's tuote_maara column, double data type.
     * @param tuote The Tuote associated with this Ostoslista for the Ostoslista table's tuote_id column, Tuote data type.
     */
    public Ostoslista(int ostoslista_id, double tuote_maara, Tuote tuote) {
    	this.ostoslista_id = ostoslista_id;
    	this.tuote_maara = tuote_maara;
    	this.tuote = tuote;
    }
    /**
     * Returns the value of ostoslista_id set for the Ostoslista object.
     * @return Integer value of Ostoslista object's ostoslista_id.
     */
	public int getOstoslista_id() {
		return ostoslista_id;
	}
	/**
	 * Set the ostoslista_id of a Ostoslista object.
	 * @param ostoslista_id The value which a Ostoslista object's ostoslista_id is to be set, integer data type.
	 */
	public void setOstoslista_id(int ostoslista_id) {
		this.ostoslista_id = ostoslista_id;
	}
	/**
	 * Returns the value of tuote_maara set for a Ostoslista object.
	 * @return Double value Ostoslista object's tuote_maara.
	 */
	public double getTuote_maara() {
		return tuote_maara;
	}
	/**
	 * Sets the tuote_maara of a Ostoslista object.
	 * @param tuote_maara The value which a Ostoslista object's tuote_maara is to be set, double data type.
	 */
	public void setTuote_maara(double tuote_maara) {
		this.tuote_maara = tuote_maara;
	}
	/**
	 * Returns the Tuote object which is associated with the Ostoslista.
	 * @return Tuote object associated with the Ostoslista.
	 */
	public Tuote getTuote() {
		return tuote;
	}
	/**
	 * Sets the Tuote object associated with the Ostoslista.
	 * @param tuote The Tuote object to be associated with the Ostoslista, Tuote object data type.
	 */
	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
	/**
	 * Generates the values of Ostoslista object into a string.
	 */
	@Override
    public String toString() {
        return "ID: " + ostoslista_id +
                ". Määrä: " + tuote_maara +
                ". Tuote: " + tuote.getTuote_nimi();
	}
}