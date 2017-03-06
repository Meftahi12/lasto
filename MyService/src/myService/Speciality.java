package myService;


import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
	
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Speciality {
	
    private  Vector<Medecin> Medecins;
    @XmlElement(name = "SpecialityName")
    private String name ;
    @XmlElement(name = "Specialitylink")
    private String link ;

    public Speciality(){
    	Medecins = new Vector<Medecin>();
    }
    
    public Vector<Medecin> getMyList() {
        return Medecins;
    }
    
    public void addMedecin(Medecin m){
    	Medecins.add(m); 
    }
    public void addMedecins(Vector<Medecin>m){
    	for(int i=0;i<m.size();i++)
    		if(m.get(i).getName() != null)
    			Medecins.add(m.get(i));
    }
    
    public void setMyList(Vector<Medecin> myList) {
    	myList = myList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setList(String url){
    	int currentPage = getCurrentPage(url);
    	Document d;
    	try {
			RestServlet.pageVisited++;
			System.out.println(url + ", "+RestServlet.pageVisited);
			d = Jsoup.connect(url).timeout(3000).get();
			
			
	    	Vector<Medecin> list = new Vector<Medecin>();
	    	for(int i= 0;i<5;i++){
	    		list.add(new Medecin());
	    	}
	    	//name 
	    	Elements a = d.select("a.span").select("span");
	    	int i = 0 ;
	    	for(Element a_span : a){
	    		list.get(i).setName(a_span.text());
	    		i++;
	    	}
	    	//adresse 
	    	i = 0 ;
	    	a = d.select("div.data").select("span.street-address");
	    	for(Element adresse : a){
	    		list.get(i).setAdresse(adresse.text());
	    		i++;
	    	}
	    	//tel
	    	i = 0 ;
	    	a = d.select("li").select("div.data");
	    	for(Element tel : a){
	    		if(isValide(tel.text())){
	    			list.get(i).setTel(validate(tel.text()));
	    			i++;
	    		}
	    	}
	    	//link
	    	
	    	i = 0 ;
	    	a = d.select("ul").select("li").select("a");
	    	for(Element link : a){
	    		if(link.text().equals("Plan et itinéraire")){
	    			list.get(i).setLink("http://www.santeaumaroc.com"+link.attr("href"));
	    			i++;
	    		}
	    	}
	    	
	    	addMedecins(list);
	    	String nextPageLink = url.substring(0,url.lastIndexOf('-')+1) + (currentPage+1) +".html";
	  //  	System.out.println(nextPageLink);
	    	a = d.select("a.page");
	    	 
	    	 for(Element link : a){
	    		if(nextPageLink.contains(link.attr("href"))){
	    			setList(nextPageLink);
	    			return;
	    		}	    			
	    	 }
	    	 java.util.Collections.sort(Medecins, new Comparator<Medecin>() {
	 		    public int compare(Medecin p1, Medecin p2) {
	 		        return p1.getName().compareTo(p2.getName());
	 		    }
	 		});
	    	 Medecin m = new Medecin();
	    	 m.setName((new Date()).toGMTString());
	    	 Medecins.add(m);
	    	
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
    public void setLink(String link) {
        this.link = link;
    }
    public int getCurrentPage(String url){
    	int nb = Integer.parseInt(url.substring(url.lastIndexOf('-')+1,url.lastIndexOf('.')));
    	return nb ;
    }
    
    public boolean isValide(String tel){
    	String acceptedChar = "0123456789 +";
    	for(int i=0;i<tel.length();i++){
    		if(!acceptedChar.contains(""+tel.charAt(i)))
    			return false ;
    	}
    	return tel.length()>8 && tel.length()<15;
    }

	public Medecin get(int indexD) {
		if(indexD<Medecins.size()){
			return Medecins.get(indexD);
		}
		Medecin m = new Medecin();
		m.setName("Not Found");
		return m ;
	}
}
