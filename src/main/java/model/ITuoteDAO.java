package model;

import java.util.ArrayList;

public interface ITuoteDAO {
	public abstract boolean createTuote(String nimi, String yksikko, int kcal);
	public abstract Tuote readTuote(int tuote_id);
	public abstract Tuote readTuoteNimi(String tuote_nimi);
	public abstract ArrayList<Object> readTuotteet();
	public abstract boolean updateTuote(String vanha_nimi, String uusi_nimi, String uusi_yksikko, int uusi_kcal);
	public abstract boolean deleteTuote(String tuote_nimi);
}