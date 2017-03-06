package myService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

public class ListPharmacieGarde {
	
	
    public static Vector<Pharmacie> myList;
    public static String lastUpdate ; 
    
    public ListPharmacieGarde() {
    	myList = new Vector<Pharmacie>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	lastUpdate = date.toGMTString();
    	setPharmacies();

		Pharmacie update = new Pharmacie();
		update.setPharmacie(ListPharmacieGarde.lastUpdate);
		java.util.Collections.sort(myList, new Comparator<Pharmacie>() {
		    public int compare(Pharmacie p1, Pharmacie p2) {
		        return p1.getSecteur().compareTo(p2.getSecteur());
		    }
		});
		ListPharmacieGarde.getMyList().add(update);
    }

    public static void setLocalisation(){
    	for(int i=0;i<myList.size();i++){
			Pharmacie p  = myList.get(i);
			p.setLocalisation(XMLParser.getGPS(p.getPharmacie()+" PHARMACIE"));
		}
    }
    
    public ListPharmacieGarde(Vector<Pharmacie> list) {
        myList = list;
    }

    public static Pharmacie get(int i) {
        return myList.get(i);
    }
   
   public void setPharmacies(){
		try {
			RestServlet.pageVisited++;
			String CodeSource = runs("http://www.pharmaciefes.com/index.php?option=com_php&Itemid=53");
			String WithoutWhiteSpaces = "";
			
			for(int i=0;i<CodeSource.length();i++){
				if(CodeSource.charAt(i) != ' ' &&CodeSource.charAt(i)!= '\n'){
					WithoutWhiteSpaces +=""+CodeSource.charAt(i);
				}
			}
			
			for(int i=0;i<ListPharmacie.getMyList().size()-1;i++){
				if(WithoutWhiteSpaces.contains(ListPharmacie.get(i).getTel()))
					if(ListPharmacie.get(i).getTel().length()==10)
						myList.add(ListPharmacie.get(i));
					else{
						if(CodeSource.contains(ListPharmacie.get(i).getPharmacie()))
							myList.add(ListPharmacie.get(i));
					}
			}
				
		} catch (IOException e) {
			RestServlet.pageNotUploaded++ ;
			e.printStackTrace();
		} 
   }
    

    public static Vector<Pharmacie> getMyList() {
        return myList;
    }

    private static String runs(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
    
}