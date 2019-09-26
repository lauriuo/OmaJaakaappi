package model;

import java.sql.Date;
import java.util.ArrayList;

public interface IJaakaappiDAO {
	public abstract boolean createJaakaappi(Date pvm, double maara, String status, int tuote_id);
	public abstract Jaakaappi readJaakaappi(int tuote_id, Date pvm);
	public abstract Jaakaappi readJaakaappiId(int jaakaappi_id);
	public abstract ArrayList<Object> readJaakaappiNimi(String tuote_nimi);
	public abstract ArrayList<Object> readJaakaapit();
	public abstract ArrayList<Object> readUsedJaakaapit();
	public abstract ArrayList<Object> readGoingOldJaakaapit();
	public abstract boolean updateJaakaappi(int jaakaappi_id, Date uusi_pvm, double uusi_maara, String uusi_status);
	public abstract boolean updateJkStatus(int jaakaappi_id, String uusi_status);
	public abstract boolean deleteJaakaappi(int jaakaappi_id);
	public abstract boolean emptyJaakaappi();
}
