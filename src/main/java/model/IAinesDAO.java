package model;

import java.util.ArrayList;

public interface IAinesDAO {
	public abstract boolean createAines(int tuote_id, int resepti_id, double maara);
	public abstract Aines readAines(String tuote_nimi, String resepti_nimi);
	public abstract Aines readAinesId(int aines_id);
	public abstract ArrayList<Object> readAinekset();
	public abstract ArrayList<Object> readAineksetReseptiID(int resepti_id);
	public abstract ArrayList<Object> readAineksetReseptiNimi(String resepti_nimi);
	public abstract ArrayList<Object> readAinesReseptit(String tuote_nimi);
	public abstract boolean updateAines(int aines_id, String uusi_tuote, String uusi_resepti, double uusi_maara);
	public abstract boolean deleteAines(int aines_id);
	public abstract boolean deleteAineksetResepti(int resepti_id);
	public abstract boolean emptyAines();
}
