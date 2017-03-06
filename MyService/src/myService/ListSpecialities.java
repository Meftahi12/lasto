package myService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Pro on 15/02/2017.
 */

public class ListSpecialities {

    public static Vector<Speciality> Specialities;
    public static String lastUpdate ;

    	
    	
    public ListSpecialities() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	lastUpdate = date.toGMTString();
    	Specialities = new Vector<Speciality>();
    	setSpecialities();
		Speciality update = new Speciality();	
		update.setName(ListSpecialities.lastUpdate);
		java.util.Collections.sort(Specialities, new Comparator<Speciality>() {
		    public int compare(Speciality p1, Speciality p2) {
		        return p1.getName().compareTo(p2.getName());
		    }
		});
		ListSpecialities.getMyList().add(update);

    }
    public static void setLocalisation(){
    	for(int i=0;i<Specialities.size();i++){
			Speciality currentSpeciality = Specialities.get(i);
			for(int j=0;j<currentSpeciality.getMyList().size();j++){
				Medecin currentMedecin = currentSpeciality.get(j);
				currentMedecin.setLocalisation(XMLParser.getGPS(currentMedecin.getAdresse()));
			}
		}
    }
    public ListSpecialities(Vector<Speciality> list) {
    	Specialities = list;
    }

    public static Speciality get(int i) {
    	if(i<Specialities.size())
    		return Specialities.get(i);
    	Speciality m = new Speciality();
		m.setName("Not Found");
		return m ;  
	}
    
    public static Speciality get(String name){
        for(int i=0;i<Specialities.size();i++){
            if(get(i).getName().equals(name))
                return get(i);
        }
        return null ;
    }
    
    
    public void setSpecialities() {
    	Document d;
		try {
			RestServlet.pageVisited++;
			System.out.println("http://www.santeaumaroc.com/medecins-par-ville/64-Fes.html" + ", "+RestServlet.pageVisited);
			d = Jsoup.connect("http://www.santeaumaroc.com/medecins-par-ville/64-Fes.html").timeout(3000).get();
			Elements ul = d.select("ul.ul_listing");
			for(Element e : ul){
				for(Element a : e.select("a")){
					Speciality c = new Speciality();
					c.setLink(a.attr("href"));
					c.setName(a.text());
					c.setMyList(null);
					c.setList("http://www.santeaumaroc.com"+c.getLink());
					Specialities.add(c);
				}				
			}	
		} catch (IOException e1) {
			RestServlet.pageNotUploaded++;
			System.out.println("Page "+RestServlet.pageVisited+" cannot be uploaded");
		}
			
    }

    public  void getSpecialities(String codeSource) throws IOException {
    	
    }

    public static Vector<Speciality> getMyList() {
        return Specialities;
    }

    public void setMyList(Vector<Speciality> myList) {
        ListSpecialities.Specialities = myList;
    }

    
    
}
