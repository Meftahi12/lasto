package myService;

import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/MedicoFes")
public class MedicoFes {
	
	public MedicoFes() {
	}
	
	@Path("/pharmacies/data")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	
	public Vector<Pharmacie> get(){
		return ListPharmacie.getMyList() ;
	}

	@Path("/pharmacies/update")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updatePharmacies(){
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		new ListPharmacie();
		return  (RestServlet.pageVisited>0 && (double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}
	
	@Path("/Specialities/data")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Speciality> getS(){
		return ListSpecialities.getMyList();
	}
	
	@Path("Specialities/{speciality}/update")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSpeciality(@PathParam("speciality") int index){
		Speciality speciality = ListSpecialities.get(index);
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		speciality.setMyList(null);
		speciality.setList("http://www.santeaumaroc.com"+speciality.getLink());
		return  ((double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}

	
	@Path("/Specialities/update")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSpecialities(){
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		new ListSpecialities();
		return  ((double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}
	
	
	@Path("/pharmacies/garde/data")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Pharmacie> getG(){
		return ListPharmacieGarde.getMyList() ;
	}
	
	

	@Path("/pharmacies/garde/update")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePharmaciesGarde(){
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		new ListPharmacieGarde();
		return  ((double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}
	
	@Path("/pharmacies/garde/GPS")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GPSPharmacies(){
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		ListPharmacieGarde.setLocalisation();
		return  ((double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}
	
	
	@Path("/Cliniques/data")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Clinique> getC(){
		return ListClinique.getCliniques();
	}	
	
	
	@Path("/Cliniques/update")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCliniques(){
		RestServlet.pageVisited = 0 ;
		RestServlet.pageNotUploaded = 0 ;
		new ListClinique();
		return  ((double)RestServlet.pageNotUploaded/RestServlet.pageVisited < 0.05)?"true":"false";	
	}
	
}
