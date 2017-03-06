package myService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Medecin")
@XmlAccessorType(XmlAccessType.FIELD)

public class Medecin {
	@XmlElement(name = "nameMedecin")
	private String name ;
	private String Adresse ;
	private String tel;
	@XmlElement(name = "linkMedecin")
	private String link;
	private MyGPS localisation ;
	
	public MyGPS getLocalisation() {
		return localisation;
	}

	public void setLocalisation(MyGPS localisation) {
		this.localisation = localisation;
	}

	public Medecin() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return Adresse;
	} 

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
