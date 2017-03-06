package myService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListClinique {
	
    private static Vector<Clinique> cliniques;
    public static String lastUpdate ;
    
    public ListClinique() {
    	cliniques = new Vector<Clinique>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	lastUpdate = date.toGMTString();
    	setCliniques();
    	Clinique update = new Clinique();
		update.setName(ListClinique.lastUpdate);
		ListClinique.getCliniques().add(update);
    }

    public ListClinique(Vector<Clinique> list) {
    	cliniques = list;
    }

    public Clinique get(int i) {
        return cliniques.get(i);
    }
   
   public void setCliniques(){
	   Document d = null ;
	   try {
		   RestServlet.pageVisited ++;
		   System.out.println("http://www.hospitalby.fr/hopital-royaume-du-maroc/hopital-fes-boulemane/" + ", "+RestServlet.pageVisited);
		   d = Jsoup.connect("http://www.hospitalby.fr/hopital-royaume-du-maroc/hopital-fes-boulemane/").timeout(3000).get();
		   Elements li =  d.select("li.forced_to_h3");
		   for(Element Cliniques : li.select("a")){
			   Clinique cl = new Clinique();
			   cl.setName(Cliniques.text());
			   cl.setAttribule(Cliniques.attr("href"));
			   cliniques.add(cl);
		   }   
	   } catch (IOException e) {
			RestServlet.pageNotUploaded++;
			System.out.println("Page "+RestServlet.pageVisited+" cannot be uploaded");
	}
   }

public static Vector<Clinique> getCliniques() {
	return cliniques;
}

public static void setCliniques(Vector<Clinique> cliniques) {
	ListClinique.cliniques = cliniques;
}     
}