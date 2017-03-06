package myService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import org.jsoup.Jsoup;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class ListPharmacie {
    private static Vector<Pharmacie> myList;
    public static String lastUpdate ; 
    
    
    public ListPharmacie() {
    	myList = new Vector<Pharmacie>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	lastUpdate = date.toGMTString();
    	setPharmacies();
    	Pharmacie update = new Pharmacie();
		update.setPharmacie(lastUpdate);
		java.util.Collections.sort(myList, new Comparator<Pharmacie>() {
		    public int compare(Pharmacie p1, Pharmacie p2) {
		        return p1.getSecteur().compareTo(p2.getSecteur());
		    }
		});
		myList.add(update);
    }
    public static void setLocalisation(){
    	for(int i=0;i<myList.size();i++){
			Pharmacie p  = myList.get(i);
			p.setLocalisation(XMLParser.getGPS(p.getPharmacie()+" PHARMACIE"));
		}
    }
    public ListPharmacie(Vector<Pharmacie> list) {
        myList = list;
    }

    public static Pharmacie get(int i) {
        return myList.get(i);
    }
   
   public void setPharmacies(){
	for (int i = 1; i <= 21; i++) 
		getPharmacies("http://www.pharmacies.i-side.net/templates/js_jamba/pharmacie/liste_phacie.php?pid=" + i); 
   }
    

    public void getPharmacies(String url) {
    	org.jsoup.nodes.Document d;
		try {
			RestServlet.pageVisited++;
			System.out.println(url + ", "+RestServlet.pageVisited);
			d = Jsoup.connect(url).timeout(3000).get();
			org.jsoup.select.Elements tr = d.select("tr#row_");
			for(org.jsoup.nodes.Element e : tr){
				Pharmacie p = new Pharmacie();
				Vector<String> Attr = new Vector<String>();
				org.jsoup.select.Elements td = e.select("td");
				for(org.jsoup.nodes.Element f : td){
					Attr.add(f.text());
				}
				p.setPharmacien(Attr.get(0).trim());
				p.setPharmacie(Attr.get(1).trim());
				p.setAdresse(Attr.get(2).trim());
				p.setSecteur(Attr.get(3).trim());
				p.setTel(validate(Attr.get(4)));
				myList.add(p);
			}
		} catch (IOException e) {
			RestServlet.pageNotUploaded++;
			System.out.println("Page "+RestServlet.pageVisited+" cannot be uploaded");
		}  
    }
    public String  validate(String s){
    	String returned  = "" ;
    	for(int i=0;i<s.length();i++){
    		if(s.charAt(i)<='9' && s.charAt(i)>='0')
    			returned+="" + s.charAt(i);
    	}
    	return returned;
    }
    public static Vector<Pharmacie> getMyList() {
        return myList;
    }

    public void setMyList(Vector<Pharmacie> myList) {
        myList = myList;
    }
    
    
}