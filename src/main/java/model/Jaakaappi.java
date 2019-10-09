package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;
/**
 * Handles the Jaakaappi objects created, corresponding the Jaakaappi table in the database.
 * @author ville
 *
 */
@Entity
@Table(name="jaakaappi")
public class Jaakaappi {
	/**
	 * A primary key column in the Jaakaappi table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
	@Id
    @Column(name="jaakaappi_id")
    private int jaakaappi_id;
	/**
	 * A column in the Jaakaappi table. A expiration date of a Tuote object associated with. Date data type. 
	 */
    @Column(name="tuote_pvm")
    private Date tuote_pvm;
    /**
	 * A column in the Jaakaappi table. Present the amount of an associated Tuote. Double data type. 
	 */
    @Column(name="tuote_maara")
    private double tuote_maara;
    /**
	 * A column in the Jaakaappi table. Present the usage status of an associated Tuote. String data type. 
	 */
    @Column(name="tuote_status")
    private String tuote_status;
    /**
	 * A foreign key column in the Aines table. Identification for a Tuote associated with the Jaakaappi. Tuote object data type.
	 */
    @ManyToOne
    @JoinColumn(name="tuote_id")
    private Tuote tuote;
        /**
	 * Initializes an empty Jaakaappi object.
	 */
    public Jaakaappi() {	
    
    }
    /**
     * Initializes a Jaakaappi object.
     * @param jaakaappi_id The identification number for the Jaakaappi table's jaakaappi_id column, integer data type.
     * @param tuote_pvm The date set as the expiration date of the Tuote associated with this Jaakaappi for the Jaakaappi table's tuote_pvm column, Date data type.
     * @param tuote_maara The amount of the associated Tuote with this Jaakaappi for the Jaakaappi table's tuote_maara column, double data type.
     * @param tuote_status The status of the Tuote associated with this Jaakaappi for the Jaakaappi table's tuote_status column, String data type.
     * @param tuote The Tuote associated with this Jaakaappi for the Jaakaappi table's tuote_id column, Tuote data type.
     */
    public Jaakaappi(int jaakaappi_id, Date tuote_pvm, double tuote_maara, String tuote_status, Tuote tuote) {
    	this.jaakaappi_id = jaakaappi_id;
    	this.tuote_pvm = tuote_pvm;
    	this.tuote_maara = tuote_maara;
    	this.tuote_status = tuote_status;
    	this.tuote = tuote;
    }
    /**
     * Returns the value of jaakaappi_id set for the Jaakaappi object.
     * @return Integer value of Jaakaappi object's jaakaappi_id.
     */
	public int getJaakaappi_id() {
		return jaakaappi_id;
	}
	/**
	 * Set the jaakaappi_id of a Jaakaappi object.
	 * @param jaakaappi_id The value which a Jaakaappi object's jaakaappi_id is to be set, integer data type.
	 */
	public void setJaakaappi_id(int jaakaappi_id) {
		this.jaakaappi_id = jaakaappi_id;
	}
	/**
	 * Return the date set as a Jaakaappi object's tuote_pvm.
	 * @return Date value of a Jaakaappi's tuote_pvm.
	 */
	public Date getTuote_pvm() {
		return tuote_pvm;
	}
	/**
	 * Sets the tuote_pvm of a Jaakaappi object.
	 * @param tuote_pvm A date to be set as new value for a Jaakaappi object's tuote_pvm, Date data type.
	 */
	public void setTuote_pvm(Date tuote_pvm) {
		this.tuote_pvm = tuote_pvm;
	}
	/**
	 * Returns the value of tuote_maara set for a Jaakaappi object.
	 * @return Double value Jaakaappi object's tuote_maara.
	 */
	public double getTuote_maara() {
		return tuote_maara;
	}
	/**
	 * Sets the tuote_maara of a Jaakaappi object.
	 * @param tuote_maara The value which a Jaakaappi object's tuote_maara is to be set, double data type.
	 */
	public void setTuote_maara(double tuote_maara) {
		this.tuote_maara = tuote_maara;
	}
	/**
	 * Return the value of tuote_status set for a Jaakaappi object.
	 * @return String value of the tuote_status set for a Jaakaappi object.
	 */
	public String getTuote_status() {
		return tuote_status;
	}
	/**
	 * Set the tuote_status of a Jaakaappi object.
	 * @param tuote_status The value which a Jaakaappi object's tuote_status is to be set, String data type. 
	 */
	public void setTuote_status(String tuote_status) {
		this.tuote_status = tuote_status;
	}
	/**
	 * Returns the Tuote object which is associated with the Jaakaappi.
	 * @return Tuote object associated with the Jaakaappi.
	 */
	public Tuote getTuote() {
		return tuote;
	}
	/**
	 * Sets the Tuote object associated with the Jaakaappi.
	 * @param tuote The Tuote object to be associated with the Jaakaappi, Tuote object data type.
	 */
	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
	/**
	 * Generates the values of Jaakaappi object into a string.
	 */
	@Override
    public String toString() {
        return "ID: " + jaakaappi_id +
                ". Viimeinen päivämäärä: " + tuote_pvm +
                ". Määrä: " + tuote_maara +
                ". Status: " + tuote_status +
                ". Tuote: " + tuote.getTuote_nimi();
	}
}
