package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Handles the Resepti objects created, corresponding the Resepti table in the database.
 * @author ville
 *
 */
@Entity
@Table(name="resepti")
public class Resepti {
	/**
	 * A primary key column in the Resepti table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
	@Id
    @Column(name="resepti_id")
    private int resepti_id;
	/**
	 * A column in the Resepti table. The name of a Resepti. String data type. 
	 */
	@Column(name="resepti_nimi")
    private String resepti_nimi;
	/**
	 * A column in the Resepti table. A written recipe. String data type.
	 */
    @Column(name="resepti_ohje")
    private String resepti_ohje;
    /**
	 * Initializes an empty Jaakaappi object.
	 */
    public Resepti() {
    }
    /**
     * Initializes a Resepti object.
     * @param resepti_id The identification number for the Resepti table's resepti_id column, integer data type.
     * @param resepti_nimi The name for the Resepti table's resepti_nimi column, String data type.
     * @param resepti_ohje The recipe for the Resepti table's resepti_ohje column, String data type.
     */
    public Resepti(int resepti_id, String resepti_nimi, String resepti_ohje) {
    	this.resepti_id = resepti_id;
    	this.resepti_nimi = resepti_nimi;
        this.resepti_ohje = resepti_ohje;
    }
    /**
     * Returns the value of resepti_id set for the Resepti object.
     * @return Integer value of Resepti object's resepti_id.
     */
	public int getResepti_id() {
		return resepti_id;
	}
	/**
	 * Set the resepti_id of an Resepti object.
	 * @param resepti_id The value which a Resepti object's resepti_id is to be set, integer data type.
	 */
	public void setResepti_id(int resepti_id) {
		this.resepti_id = resepti_id;
	}
	/**
	 * Return the value of resepti_ohje set for a Jaakaappi object.
	 * @return String value of the resepti_ohje set for a Resepti object.
	 */
	public String getResepti_ohje() {
		return resepti_ohje;
	}
	/**
	 * Set the resepti_ohje of a Resepti object.
	 * @param resepti_ohje The value which a Resepti object's resepti_ohje is to be set, String data type.
	 */
	public void setResepti_ohje(String resepti_ohje) {
		this.resepti_ohje = resepti_ohje;
	}
	/**
	 * Returns the value of resepti_nimi set for a Resepti object.
	 * @return String value of resepti_nimi of a Resepti object.
	 */
	public String getResepti_nimi() {
		return resepti_nimi;
	}
	/**
	 * Set the resepti_nimi of a Resepti object.
	 * @param resepti_nimi The value which a Tuote object's tuote_nimi is the be set, String data type.
	 */
	public void setResepti_nimi(String resepti_nimi) {
		this.resepti_nimi = resepti_nimi;
	}
	/**
	 * Generates the values of Resepti object into a string.
	 */
	@Override
    public String toString() {
        return "Resepti ID: " + resepti_id +
        		". Reseptin nimi: " + resepti_nimi +
                ". Ohje: " + resepti_ohje;
    }
}
