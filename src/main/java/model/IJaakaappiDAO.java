package model;

import java.sql.Date;
import java.util.ArrayList;
/**
 * The IJaakaappiDAO interface provides all the methods necessary for communicating with the Jaakaappi table in the database.
 * @author ville
 *
 */
public interface IJaakaappiDAO {
	/**
	 * For creating a new record in the Jaakaappi table.
	 * @param pvm The expiration date of the Tuote being placed in the Jaakaappi.
	 * @param maara The amount of the Tuote being placed in the Jaakaappi.
	 * @param status The usability status of the Tuote being placed in the Jaakaappi.
	 * @param tuote_id The ID of the Tuote being placed in the fridge.
	 * @return Returns true if the new record was created successfully into the database.
	 */
	public abstract boolean createJaakaappi(Date pvm, double maara, String status, int tuote_id);
	/**
	 * For reading a Jaakaappi record from the database using the ID and the expiration date of the Tuote to read it.
	 * @param tuote_id The ID of the Tuote in the Jaakaappi to be read.
	 * @param pvm The expiration date of the Tuote which is being read from the Jaakaappi.
	 * @return Returns the Jaakaappi record form the database which was read.
	 */
	public abstract Jaakaappi readJaakaappi(int tuote_id, Date pvm);
	/**
	 * For reading a Jaakaappi record from the database, using a ID of a Jaakaappi to read it.
	 * @param jaakaappi_id The ID of the Jaakaappi which is being read.
	 * @return Returns the Jaakaappi record from the database with the matching ID.
	 */
	public abstract Jaakaappi readJaakaappiId(int jaakaappi_id);
	/**
	 * For reading all the Jaakaappi record from the database with the matching Tuote name.
	 * @param tuote_nimi The name of the Tuote associated with the Jaakaappi records to be read.
	 * @return Returns an ArrayList with all the Jaakaappi records with the specific Tuote name.
	 */
	public abstract ArrayList<Object> readJaakaappiNimi(String tuote_nimi);
	/**
	 * For reading all the Jaakaappi records from the database.
	 * @return Returns an ArrayList of all the Jaakaappi records.
	 */
	public abstract ArrayList<Object> readJaakaapit();
	/**
	 * For reading all the Jaakaappi records from the database with the status set as "Käytetty".
	 * @return An ArrayList of all the Jaakaappi records with the status "Käytetty.
	 */
	public abstract ArrayList<Object> readUsedJaakaapit();
	/**
	 * For reading all the Jaakaappi records from the database with the status set as "Hävikki".
	 * @return An ArrayList of all the Jaakaappi records with the status "Hävikki.
	 */
	public abstract ArrayList<Object> readWasteJaakaapit();
	/**
	 * For reading all the Jaakaappi records from the database with the expiration date being in the next two days or less.
	 * @return An ArrayList of Jaakaappi records with the expritaion dates being two days or less away.
	 */
	public abstract ArrayList<Object> readGoingOldJaakaapit();
	/**
	 * For updating a Jaakaappi record in the database.
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @param uusi_pvm The new expiration date to be set for the Jaakaappi record.
	 * @param uusi_maara The new amount of the Tuote in the Jaakaappi record.
	 * @param uusi_status The new status for the Tuote in the Jaakaappi record.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	public abstract boolean updateJaakaappi(int jaakaappi_id, Date uusi_pvm, double uusi_maara, String uusi_status);
	/**
	 * For updating the status of a Jaakaappi record to "Käytetty".
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	public abstract boolean updateJkKaytetty(int jaakaappi_id);
	/**
	 * For updating the status of a Jaakaappi record to "Hävikki".
	 * @param jaakaappi_id The ID of the Jaakaappi record to be updated.
	 * @return Returns true if the update was successful. Returns false if the update failed.
	 */
	public abstract boolean updateJkHavikki(int jaakaappi_id);
	/**
	 * For deleting a Jaakaappi record from the database.
	 * @param jaakaappi_id The ID of the Jaakaappi record to be deleted.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean deleteJaakaappi(int jaakaappi_id);
	/**
	 * For splitting a Jaakaappi record into two new ones with the different amount of Tuote.
	 * @param jaakaappi_id The ID of the Jaakaappi to be split.
	 * @param maara The amount of the Tuote in the original Jaakaappi record to be put into a new record.
	 * @param kaytetty_tai_havikki The status for the new Jaakaappi record being made with the splitting.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean updateJkMaara(int jaakaappi_id, double maara, String kaytetty_tai_havikki);
	/**
	 * For emptying the whole Jaakaappi table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyJaakaappi();
}
