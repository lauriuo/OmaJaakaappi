package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="jaakaappi")
public class Jaakaappi {
	@Id
    @Column(name="jaakaappi_id")
    private int jaakaappi_id;

    @Column(name="tuote_pvm")
    private Date tuote_pvm;

    @Column(name="tuote_maara")
    private double tuote_maara;
    
    @Column(name="tuote_status")
    private String tuote_status;

    @ManyToOne
    @JoinColumn(name="tuote_id")
    private Tuote tuote;
    
    
    public Jaakaappi() {	
    
    }
    
    public Jaakaappi(int jaakaappi_id, Date tuote_pvm, double tuote_maara, String tuote_status, Tuote tuote) {
    	this.jaakaappi_id = jaakaappi_id;
    	this.tuote_pvm = tuote_pvm;
    	this.tuote_maara = tuote_maara;
    	this.tuote_status = tuote_status;
    	this.tuote = tuote;
    }

	public int getJaakaappi_id() {
		return jaakaappi_id;
	}

	public void setJaakaappi_id(int jaakaappi_id) {
		this.jaakaappi_id = jaakaappi_id;
	}

	public Date getTuote_pvm() {
		return tuote_pvm;
	}

	public void setTuote_pvm(Date tuote_pvm) {
		this.tuote_pvm = tuote_pvm;
	}

	public double getTuote_maara() {
		return tuote_maara;
	}

	public void setTuote_maara(double tuote_maara) {
		this.tuote_maara = tuote_maara;
	}

	public String getTuote_status() {
		return tuote_status;
	}

	public void setTuote_status(String tuote_status) {
		this.tuote_status = tuote_status;
	}

	public Tuote getTuote() {
		return tuote;
	}

	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}
    
	@Override
    public String toString() {
        return "ID: " + jaakaappi_id +
                ". Viimeinen päivämäärä: " + tuote_pvm +
                ". Määrä: " + tuote_maara +
                ". Status: " + tuote_status +
                ". Tuote: " + tuote.getTuote_nimi();
	}
}
