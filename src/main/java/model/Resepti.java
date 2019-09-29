package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resepti")
public class Resepti {
	
	@Id
    @Column(name="resepti_id")
    private int resepti_id;

	@Column(name="resepti_nimi")
    private String resepti_nimi;
	
    @Column(name="resepti_ohje")
    private String resepti_ohje;

    public Resepti() {
    }
    
    public Resepti(int resepti_id, String resepti_ohje) {
    	this.resepti_id = resepti_id;
        this.resepti_ohje = resepti_ohje;
    }

	public int getResepti_id() {
		return resepti_id;
	}

	public void setResepti_id(int resepti_id) {
		this.resepti_id = resepti_id;
	}

	public String getResepti_ohje() {
		return resepti_ohje;
	}

	public void setResepti_ohje(String resepti_ohje) {
		this.resepti_ohje = resepti_ohje;
	}
	
	public String getResepti_nimi() {
		return resepti_nimi;
	}

	public void setResepti_nimi(String resepti_nimi) {
		this.resepti_nimi = resepti_nimi;
	}

	@Override
    public String toString() {
        return "Resepti ID: " + resepti_id +
        		". Reseptin nimi: " + resepti_nimi +
                ". Ohje: " + resepti_ohje;
    }
}
