package myService;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pharmacie {
	 	private String pharmacien ;
	 	
	 	@XmlElement(name="pharmacieName")
	    private String pharmacie;
	    private String adresse;
	    private String secteur;
	    private String tel;
	    private MyGPS localisation ;


		public MyGPS getLocalisation() {
			return localisation;
		}


		public void setLocalisation(MyGPS localisation) {
			this.localisation = localisation;
		}


		public Pharmacie(){
	    	
	    }
	  

		public String getPharmacien() {
	        return pharmacien;
	    }
	    @Override
		public String toString(){
	        return pharmacien+","+pharmacie+","+adresse+","+secteur+","+tel ;
	    }

	    public void setPharmacien(String pharmacien) {
	        this.pharmacien = pharmacien;
	    }

	    public String getPharmacie() {
	        return pharmacie;
	    }

	    public void setPharmacie(String pharmacie) {
	        this.pharmacie = pharmacie;
	    }

	    public String getAdresse() {
	        return adresse;
	    }

	    public void setAdresse(String adresse) {
	        this.adresse = adresse;
	    }

	    public String getSecteur() {
	        return secteur;
	    }

	    public void setSecteur(String secteur) {
	        this.secteur = secteur;
	    }

	    public String getTel() {
	        return tel;
	    }

	    public void setTel(String tel) {
	        this.tel = tel;
	    }
}
