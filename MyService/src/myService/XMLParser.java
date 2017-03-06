package myService;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pro on 19/02/2017.
 */

public class XMLParser {

    

  
    public static MyGPS getGPS(String name){
    	if(RestServlet.localisation.containsKey(name))
    		return RestServlet.localisation.get(name);
    	
        String URL = toURL(name);
        RestServlet.pageVisited++;
        System.out.println("http://maps.google.com/maps/api/geocode/xml?address=FES%20"+URL+"%20&sensor=false"+","+RestServlet.pageVisited);
        String content = HttpManager.getDatas("http://maps.google.com/maps/api/geocode/xml?address=FES%20"+URL+"%20&sensor=false");
        try{
            double x = 0 ;
            double y = 0 ;
            boolean isXFound = false ;
            boolean isYFound = false ;
            String currentTagName = "";
            MyGPS GPS = new MyGPS();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser  = factory.newPullParser();
            parser.setInput(new StringReader(content));
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        break;
                    case XmlPullParser.END_TAG:
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        switch (currentTagName){
                            case "lat":
                                isXFound = true ;
                                GPS.setX(Double.parseDouble(parser.getText()));
                                break;
                            case "lng":
                                isYFound = true ;
                                GPS.setY(Double.parseDouble(parser.getText()));
                                break;
                            default:
                                break;
                        }
                }
                if(isXFound && isYFound){
                	RestServlet.localisation.put(name, GPS);
                    return GPS ;
                }

                eventType = parser.next();
            }
            return null;
        }
        catch (Exception e){
        	RestServlet.pageNotUploaded++;
        	System.out.println("not uploaded");
            e.printStackTrace();
            return null ;
        }
    }
    public static String toURL(String name){
        String URL = "";
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)==' ')
                URL+="%20";
            else
                URL+=""+name.charAt(i);
        }
        return URL;
    }
}
