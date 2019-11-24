package model;

import java.sql.Date;
import java.util.ArrayList;
/**
 * The IOstoslistaDAO interface provides all the methods necessary for communicating with the Ostoslista table in the database.
 * @author ville
 *
 */
public interface IOstoslistaDAO {
	/**
	 * For creating a new record in the Ostoslista table.
	 * @param maara The amount of the Tuote being placed in the Ostoslista.
	 * @param tuote_id The ID of the Tuote being placed in the fridge.
	 * @return Returns true if the new record was created successfully into the database.
	 */
	public abstract boolean createOstoslista(double maara, int tuote_id);
	/**
	 * For reading a Ostoslista record from the database using the ID and the expiration date of the Tuote to read it.
	 * @param tuote_id The ID of the Tuote in the Ostoslista to be read.
	 * @return Returns the Ostoslista record form the database which was read.
	 */
	public abstract Ostoslista readOstoslista(int tuote_id);
	/**
	 * For reading a Ostoslista record from the database, using a ID of a Ostoslista to read it.
	 * @param ostoslista_id The ID of the Ostoslista which is being read.
	 * @return Returns the Ostoslista record from the database with the matching ID.
	 */
	public abstract Ostoslista readOstoslistaId(int ostoslista_id);
	/**
	 * For reading all the Ostoslista record from the database with the matching Tuote name.
	 * @param tuote_nimi The name of the Tuote associated with the Ostoslista records to be read.
	 * @return Returns an ArrayList with all the Ostoslista records with the specific Tuote name.
	 */
	public abstract ArrayList<Object> readOstoslistaName(String tuote_nimi);
	/**
	 * For reading all the Ostoslista records from the database.
	 * @return Returns an ArrayList of all the Ostoslista records.
	 */
	public abstract ArrayList<Object> readOstoslistat();
	/**
	 * For deleting a Ostoslista record from the database.
	 * @param ostoslista_id The ID of the Ostoslista record to be deleted.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean deleteOstoslista(int ostoslista_id);
	/**
	 * For emptying the whole Ostoslista table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyOstoslista();
}