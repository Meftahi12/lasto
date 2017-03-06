package myService;

import java.io.IOException;
import java.util.Vector;
import javax.xml.bind.annotation.XmlRootElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@XmlRootElement 
public class Clinique {
	
	private String name ;
	private String gps;
	private String emplacement;
	private String adresse ;
	
	public Clinique(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setAttribule(String url){
		Document d = null ;
		try {
			RestServlet.pageVisited++;
			System.out.println(url + ", "+RestServlet.pageVisited);
			d = Jsoup.connect(url).timeout(3000).get();
			Elements e = d.select("div");
			Vector<String> Coordonnées = new Vector<String>();
			for(int i=0;i<3;i++){
				Coordonnées.add(new String());
			}
			int i = 0 ;
			for(Element at : e){
				if(at.hasAttr("style") && at.attr("style").equals("float:left;width:76%") && i<3){
					Coordonnées.set(i,at.text());
					i++ ;
				}
			}
			setGps(Coordonnées.get(0));
			setEmplacement(Coordonnées.get(1));
			setAdresse(Coordonnées.get(2));
		} catch (IOException e1) {
			RestServlet.pageNotUploaded++;
			System.out.println("Page "+RestServlet.pageVisited+" cannot be uploaded");
		}	
	}
}
