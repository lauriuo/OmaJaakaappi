package model;

import java.util.ArrayList;
/**
 * The ITuoteDAO interface provides all the methods necessary for communicating with the Tuote table in the database.
 * @author ville
 *
 */
public interface ITuoteDAO {
	/**
	 * For creating a new record into the Tuote table.
	 * @param nimi The name of the Tuote.
	 * @param yksikko The unit used for measuring the Tuote.
	 * @param kcal The calories per measuring unit in the Tuote.
	 * @param suola The amount of salt per measuring unit in the Tuote.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean createTuote(String nimi, String yksikko, double kcal, double suola);
	/**
	 * For reading a Tuote record from the database using the Tuote record's ID to read it.
	 * @param tuote_id The ID of the Tuote to be read.
	 * @return Return the Tuote record with the matching ID.
	 */
	public abstract Tuote readTuote(int tuote_id);
	/**
	 * For reading a Tuote record from the database using the name of the Tuote to read it.
	 * @param tuote_nimi The name of the Tuote to be read.
	 * @return Returns the Tuote record with the matching name.
	 */
	public abstract Tuote readTuoteNimi(String tuote_nimi);
	/**
	 * For reading all the Tuote records from the database.
	 * @return An ArrayList of all the Tuote records.
	 */
	public abstract ArrayList<Object> readTuotteet();
	/**
	 * For updating a Tuote record in the database.
	 * @param vanha_nimi The name of the Tuote record to be updated.
	 * @param uusi_nimi The new name of the Tuote.
	 * @param uusi_yksikko The new unit for the Tuote.
	 * @param uusi_kcal The new amount of calories.
	 * @param uusi_suola The new amount of salt.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean updateTuote(String vanha_nimi, String uusi_nimi, String uusi_yksikko, double uusi_kcal, double uusi_suola);
	/**
	 * For deleting a Tuote record from the database.
	 * @param tuote_nimi The name of the Tuote record to be deleted.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	public abstract boolean deleteTuote(String tuote_nimi);
	/**
	 * For emptying the whole Tuote table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyTuote();
}