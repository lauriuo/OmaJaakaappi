package model;

import java.sql.Date;
import java.util.ArrayList;
/**
 * The IRpkDAO interface provides all the methods necessary for communicating with the Rpk table in the database.
 * @author ville
 *
 */
public interface IRpkDAO {
	/**
	 * For creating a new record into the Rpk table.
	 * @param rpk_pvm The date on which the Rpk record was created.
	 * @param jaakaappi_id The ID of the Jaakaappi record associated with the Rpk record.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean createRpk(Date rpk_pvm, int jaakaappi_id);
	/**
	 * For reading a Rpk record from the database using the ID of the Rpk record to read it.
	 * @param rpk_id The ID of the Rpk record to be read.
	 * @return Returns the Rpk record which was read.
	 */
	public abstract Rpk readRpkId(int rpk_id);
	/**
	 * For reading a Rpk record from the database using the Jaakaappi record's ID associated with the Rpk.
	 * @param jaakaappi_id The ID of the Jaakaappi record associated with the Rpk record to be read.
	 * @return Return the Rpk record which was read.
	 */
	public abstract Rpk readRpkJkId(int jaakaappi_id);
	/**
	 * For reading all the Rpk records sharing the same date.
	 * @param rpk_pvm The date on which the Rpk records were made.
	 * @return An ArrayList of all the Rpk records matching the date.
	 */
	public abstract ArrayList<Object> readRpkPvm(Date rpk_pvm);
	/**
	 * For reading all the Rpk record from the database.
	 * @return An ArrayList of all the Rpk records from the database.
	 */
	public abstract ArrayList<Object> readRpkt();
	/**
	 * For updating a Rpk record in the database.
	 * @param rpk_id The ID of the Rpk record to be updated.
	 * @param rpk_pvm The new date to be set for the Rpk record.
	 * @return Returns true if the operation was successful. Returns false if the operation failed.
	 */
	public abstract boolean updateRpk(int rpk_id, Date rpk_pvm);
	/**
	 * For deleting a Rpk record from the database.
	 * @param rpk_id The ID of the Rpk record to be deleted from the database.
	 * @return Return true if the records were deleted from the table successfully. Returns false in other cases.
	 */
	public abstract boolean deleteRpk(int rpk_id);
	/**
	 * For emptying the whole Rpk table of the database.
	 * @return Returns true if the operation was successful. Returns false in other cases.
	 */
	public abstract boolean emptyRpk();
}
