package model;

import java.sql.Date;
import java.util.ArrayList;

public interface IRpkDAO {
	public abstract boolean createRpk(Date rpk_pvm, int jaakaappi_id);
	public abstract Rpk readRpkId(int rpk_id);
	public abstract Rpk readRpkJkId(int jaakaappi_id);
	public abstract ArrayList<Object> readRpkPvm(Date rpk_pvm);
	public abstract ArrayList<Object> readRpkt();
	public abstract boolean updateRpk(int rpk_id, Date rpk_pvm);
	public abstract boolean deleteRpk(int rpk_id);
	public abstract boolean emptyRpk();
}
