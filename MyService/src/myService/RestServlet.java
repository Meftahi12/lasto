package myService;

import java.awt.Point;
import java.util.HashMap;

import javax.servlet.ServletException;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class RestServlet extends ServletContainer{
	public static HashMap<String, MyGPS> localisation = new HashMap<String,MyGPS>();
	public static int pageVisited = 0 ;
	public static int pageNotUploaded = 0 ;
	
	@Override
	public void init() throws ServletException {
	
		super.init();
		long startTime = System.nanoTime();
		new ListPharmacie();
		new ListPharmacieGarde();
		new ListSpecialities();
		new ListClinique();
		//ListPharmacie.setLocalisation();
		//ListSpecialities.setLocalisation();
		long elapsedTimeNs = System.nanoTime() - startTime;
		int nb = (int) (100 * (double)pageNotUploaded/pageVisited);
		System.out.println("Time  ===> "+elapsedTimeNs/Math.pow(10, 9)+" , Pages to be dowloaded ==> "+pageVisited +" , "+ nb + "% Of pages are not downloaaded");
	
	}
}
