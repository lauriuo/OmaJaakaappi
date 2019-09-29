package model;

import java.util.ArrayList;

public interface IReseptiDAO {
	public abstract boolean createResepti(String resepti_nimi, String resepti_ohje);
	public abstract Resepti readReseptiNimi(String resepti_nimi);
	public abstract Resepti readReseptiId(int resepti_id);
	public abstract ArrayList<Object> readReseptit();
	public abstract boolean updateResepti(int resepti_id,String uusi_nimi, String uusi_ohje);
	public abstract boolean deleteResepti(int resepti_id);
	public abstract boolean emptyResepti();
}
