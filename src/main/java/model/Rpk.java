package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Handles the Rpk objects created, corresponding the Rpk table in the database.
 * @author ville
 *
 */
@Entity
@Table(name="rpk")
public class Rpk {
	/**
	 * A primary key column in the Rpk table. Used to identify the records. Integer data type, auto incremented by the database. 
	 */
	@Id
    @Column(name="rpk_id")
    private int rpk_id;
	/**
	 * A column in the Rpk table. Date on which the record were made. Date data type.
	 */
    @Column(name="rpk_pvm")
    private Date rpk_pvm;
    /**
	 * A foreign key column in the Rpk table. Identification for a Jaakaappi associated with the Rpk. Jaakaappi object data type.
	 */
    @OneToOne
    @JoinColumn(name="jaakaappi_id")
    private Jaakaappi jaakaappi;
    /**
	 * Initializes an empty Rpk object.
	 */
    public Rpk() {	
    
    }
    /**
     * Initializes an Rpk object.
     * @param rpk_pvm The date on which the Rpk object was created for the Rpk table's rpk_pvm column, Date data type.
     * @param jaakaappi The Jaakaappi associated with this Rpk for the Rpk table's jaakaappi_id column, Jaakaappi data type.
     */
    public Rpk(Date rpk_pvm, Jaakaappi jaakaappi) {
    	this.rpk_pvm = rpk_pvm;
    	this.jaakaappi = jaakaappi;
    }
    /**
     * Returns the value of rpk_id set for the Rpk object.
     * @return Integer value of Rpk object's rpk_id.
     */
	public int getRpk_id() {
		return rpk_id;
	}
	/**
	 * Set the rpk_id of a Rpk object.
	 * @param rpk_id The value which a Rpk object's rpk_id is to be set, integer data type.
	 */
	public void setRpk_id(int rpk_id) {
		this.rpk_id = rpk_id;
	}
	/**
	 * Return the date set as a Rpk object's rpk_pvm.
	 * @return Date value of a Rpk object's rpk_pvm.
	 */
	public Date getRpk_pvm() {
		return rpk_pvm;
	}
	/**
	 * Sets the rpk_pvm of a Rpk object.
	 * @param rpk_pvm A date to be set as new value for a Rpk object's rpk_pvm, Date data type.
	 */
	public void setRpk_pvm(Date rpk_pvm) {
		this.rpk_pvm = rpk_pvm;
	}
	/**
	 * Returns the Jaakaappi object which is associated with the Rpk.
	 * @return Jaakaappi object associated with the Rpk.
	 */
	public Jaakaappi getJaakaappi() {
		return jaakaappi;
	}
	/**
	 * Sets the Jaakaappi object associated with the Rpk.
	 * @param jaakaappi The Jaakaappi object to be associated with the Rpk, Jaakaappi object data type.
	 */
	public void setJaakaappi(Jaakaappi jaakaappi) {
		this.jaakaappi = jaakaappi;
	}
	/**
	 * Generates the values of Rpk object into a string.
	 */
	@Override
    public String toString() {
        return "rpk_id: " + rpk_id +
                ". jaakaappi_id: " + jaakaappi.getJaakaappi_id() +
                ". Päivämäärä: " + rpk_pvm;
    }
}
