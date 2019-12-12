package model;

import java.util.ArrayList;
/**
 * The IAinesDAO interface provides all the methods necessary for communicating with the Aines table in the database.
 * @author ville
 *
 */
public interface IAinesDAO {
	/**
	 * For creating a new record in the Aines table.
	 * @param tuote_id The ID of a Tuote associated with the Aines.
	 * @param resepti_id The ID of a Resepti associated with the Aines.
	 * @param maara The amount of associated Tuote of the Aines.
	 * @return Returns true if creation was successful, false in other cases.
	 */
	public abstract boolean createAines(int tuote_id, int resepti_id, double maara);
	/**
	 * For reading a specific Aines record from the database, using the associated Tuote and Resepti to define which Aines.
	 * @param tuote_nimi The name of the Tuote associated with the Aines to be read.
	 * @param resepti_nimi The name of the Resepti associated with the Aines to be read.
	 * @return Returns the Aines record from the database.
	 */
	public abstract Aines readAines(String tuote_nimi, String resepti_nimi);
	/**
	 * For reading a specific Aines record from the database, using the ID of the Aines.
	 * @param aines_id The ID of the Aines which record is wanted.
	 * @return The Aines which was read.
	 */
	public abstract Aines readAinesId(int aines_id);
	/**
	 * For reading all the Aines records in the database.
	 * @return Arraylist of all the Aines records in the database.
	 */
	public abstract ArrayList<Object> readAinekset();
	/**
	 * For reading all the Aines records from the database that are associated with a specific Resepti's ID.
	 * @param resepti_id The ID of the Resepti which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records which are associated with the resepti_id.
	 */
	public abstract ArrayList<Object> readAineksetReseptiID(int resepti_id);
	/**
	 * For reading all the Aines records from the database which are associated with a specific Resepti's name.
	 * @param resepti_nimi The name of the Resepti which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records with are associated with the name of the Resepti.
	 */
	public abstract ArrayList<Object> readAineksetReseptiNimi(String resepti_nimi);
	/**
	 * For reading all the Aines records from the database that are associated with specific Tuote's name.
	 * @param tuote_nimi The name of the Tuote which is associated with the Aines records to be read.
	 * @return Arraylist of all the Aines records that are associated with the name of the Tuote.
	 */
	public abstract ArrayList<Object> readAinesReseptit(String tuote_nimi);
	/**
	 * For updating a record in the Aines table of the database.
	 * @param aines_id The ID of the Aines to be updated.
	 * @param uusi_tuote The name of the new Tuote to be associated with the Aines.
	 * @param uusi_resepti The name of the new Resepti to be associated with the Aines.
	 * @param uusi_maara The new amount of associated Tuote used.
	 * @return Returns true if the update was successful. Returns false in other cases.
	 */
	public abstract boolean updateAines(int aines_id, String uusi_tuote, String uusi_resepti, double uusi_maara);
	/**
	 * For deleting a record from the Aines table in the database.
	 * @param aines_id The ID of the Aines to be removed from the table.
	 * @return Return true if the record was deleted from the table successfully. Returns false in other cases.
	 */
	public abstract boolean deleteAines(int aines_id);
	/**
	 * For deleting all the Aines records from the table which are associated with specific Resepti.
	 * @param resepti_id The ID of the Resepti which associated Aines records will be deleted.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	public abstract boolean deleteAineksetResepti(int resepti_id);
	/**
	 * For emptying the whole Aines table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyAines();
	/**
	 * For selecting from the Aines table the ingredients that are in Jaakaappi and in the given recipe
	 * @param resepti_id The ID of the recipe in question.
	 * @return Returns an ArrayList of Aines objects if the operation was successful. Returns null otherwise.
	 */
	public abstract ArrayList<Object> availableForResepti(int resepti_id);
	/**
	 * For selecting from the Aines table the ingredients that are not in Jaakaappi, but are in the given recipe
	 * @param resepti_id The ID of the recipe in question.
	 * @return Returns an ArrayList of Aines objects if the operation was successful. Returns null otherwise.
	 */
	public abstract ArrayList<Object> notAvailableForResepti(int resepti_id);
}
