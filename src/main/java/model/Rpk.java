package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rpk")
public class Rpk {
	@Id
    @Column(name="rpk_id")
    private int rpk_id;

    @Column(name="rpk_pvm")
    private Date rpk_pvm;
    
    @OneToOne
    @JoinColumn(name="jaakaappi_id")
    private Jaakaappi jaakaappi;
    
    public Rpk() {	
    
    }
    
    public Rpk(Date rpk_pvm, Jaakaappi jaakaappi) {
    	this.rpk_pvm = rpk_pvm;
    	this.jaakaappi = jaakaappi;
    }

	public int getRpk_id() {
		return rpk_id;
	}

	public void setRpk_id(int rpk_id) {
		this.rpk_id = rpk_id;
	}

	public Date getRpk_pvm() {
		return rpk_pvm;
	}

	public void setRpk_pvm(Date rpk_pvm) {
		this.rpk_pvm = rpk_pvm;
	}

	public Jaakaappi getJaakaappi() {
		return jaakaappi;
	}

	public void setJaakaappi(Jaakaappi jaakaappi) {
		this.jaakaappi = jaakaappi;
	}
    
	@Override
    public String toString() {
        return "rpk_id: " + rpk_id +
                ". jaakaappi_id: " + jaakaappi.getJaakaappi_id() +
                ". Päivämäärä: " + rpk_pvm;
    }
}
