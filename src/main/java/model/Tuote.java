package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Handles the Tuote objects created, corresponding the Tuote table in the database.
 * @author ville
 *
 */
@Entity
@Table(name="tuote")
public class Tuote {
	/**
	 * A primary key column in the Tuote table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
    @Id
    @Column(name="tuote_id")
    private int tuote_id;
    /**
	 * A column in the Tuote table. The name of a Tuote. String data type. 
	 */
    @Column(name="tuote_nimi")
    private String tuote_nimi;
    /**
	 * A column in the Tuote table. The unit used to measure the Tuote. String data type. 
	 */
    @Column(name="tuote_yksikko")
    private String tuote_yksikko;
    /**
     * A column in the Tuote table. The amount of calories contained in the chosen unit. Double data type.
     */
    @Column(name="tuote_kcal")
    private double tuote_kcal;
    /**
	 * Initializes an empty Tuote object.
	 */
    public Tuote () {
    }
    /**
     * Initializes a Tuote object. 
     * @param tuote_id The identification number for the Tuote table's tuote_id column, integer data type.
     * @param tuote_nimi The name for the Tuote table's tuote_nimi column, String data type.
     * @param tuote_yksikko The unit used for the Tuote for the Tuote table's tuote_yksikko column, String data type.
     * @param tuote_kcal The amount of calories per unit in the Tuote for the Tuote table's tuote_kcal column, double data type.
     */
    public Tuote (int tuote_id, String tuote_nimi,
     String tuote_yksikko, double tuote_kcal) {
        this.tuote_id = tuote_id;
        this.tuote_kcal = tuote_kcal;
        this.tuote_yksikko = tuote_yksikko;
        this.tuote_nimi = tuote_nimi;
    }
    /**
     * Returns the value of tuote_id set for the Tuote object.
     * @return Integer value of Tuote object's tuote_id.
     */
    public int getTuote_id() {
        return tuote_id;
    }
    /**
     * Set the tuote_id of a Tuote object.
     * @param tuote_id The value which a Tuote object's tuote_id is to be set, integer data type.
     */
    public void setTuote_id(int tuote_id) {
    	this.tuote_id = tuote_id;
    }
    /**
     * Returns the value of tuote_kca set for a Tuote object.
     * @return Double value Tuote object's tuote_kcal.
     */
    public double getTuote_kcal() {
        return tuote_kcal;
    }
    /**
     * Set the tuote_kcal of a Tuote object.
     * @param tuote_kcal The value which a Tuote object's tuote_kcal is to be set, double data type.
     */
    public void setTuote_kcal(double tuote_kcal) {
    	this.tuote_kcal = tuote_kcal;
    }
    /**
	 * Returns the value of tuote_nimi set for a Tuote object.
	 * @return String value of tuote_nimi of a Tuote object.
	 */
    public String getTuote_nimi() {
        return tuote_nimi;
    }
    /**
     * Set the tuote_nimi of a Tuote object.
     * @param tuote_nimi The value which a Tuote object's tuote_nimi is the be set, String data type.
     */
    public void setTuote_nimi(String tuote_nimi) {
    	this.tuote_nimi = tuote_nimi;
    }
    /**
     * Return the value of tuote_yksikko set for a Tuote object.
     * @return String value of the tuote_yksikko set for a Tuote object.
     */
    public String getTuote_yksikko() {
        return tuote_yksikko;
    }
    /**
     * Set the tuote_yksikko of a Tuote object.
     * @param yksikko The value which a Tuote object's tuote_yksikko is to be set, String data type. 
     */
    public void setTuote_yksikko(String yksikko) {
    	this.tuote_yksikko = yksikko;
    }
    /**
	 * Generates the values of Jaakaappi object into a string.
	 */
    @Override
    public String toString() {
        return tuote_nimi +
                ". Calories: " + tuote_kcal +
                ". Unit: " + tuote_yksikko;
    }
}