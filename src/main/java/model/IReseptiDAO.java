package model;

import java.util.ArrayList;
/**
 * The IReseptiDAO interface provides all the methods necessary for communicating with the Resepti table in the database.
 * @author ville
 *
 */
public interface IReseptiDAO {
	/**
	 * For creating a new record in the Resepti table.
	 * @param resepti_nimi The name for the Resepti.
	 * @param resepti_ohje The recipe for the Resepti.
	 * @return Returns true if the insert was successful. Returns false in other cases.
	 */
	public abstract boolean createResepti(String resepti_nimi, String resepti_ohje);
	/**
	 * For reading a Resepti from the database, using a name of a Resepti to read it.
	 * @param resepti_nimi The name of the Resepti which is being read.
	 * @return Returns a Resepti from the database with the matching name.
	 */
	public abstract Resepti readReseptiNimi(String resepti_nimi);
	/**
	 * For reading a Resepti from the database, using a ID of a Resepti to read it. 
	 * @param resepti_id The ID of the Resepti which is being read.
	 * @return Returns a Resepti from the database with the matching ID.
	 */
	public abstract Resepti readReseptiId(int resepti_id);
	/**
	 * For reading all the Resepti records from the database.
	 * @return ArrayList with all the Resepti records from the database.
	 */
	public abstract ArrayList<Object> readReseptit();
	/**
	 * For updating a Resepti record in the database.
	 * @param resepti_id The ID of the Resepti to be updated.
	 * @param uusi_nimi The new name for the Resepti which is being updated.
	 * @param uusi_ohje The new recipe for the Resepti which is being updated.
	 * @return Returns true if the update was successful. Returns false in other cases.
	 */
	public abstract boolean updateResepti(int resepti_id,String uusi_nimi, String uusi_ohje);
	/**
	 * For deleting a Resepti record from the database.
	 * @param resepti_id The ID of the Resepti which is being removed.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	public abstract boolean deleteResepti(int resepti_id);
	/**
	 * For emptying the whole Resepti table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyResepti();
	public abstract double countKcalResepti(int resepti_id);
}
