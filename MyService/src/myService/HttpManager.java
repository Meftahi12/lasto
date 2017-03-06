package myService;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Pro on 19/02/2017.
 */

public class HttpManager {

    public static String getDatas(String url){
        BufferedReader br  ;
        try{
            URL Url = new URL(url);
            HttpURLConnection htc = (HttpURLConnection) Url.openConnection();
            htc.setConnectTimeout(1000*70);
            br = new BufferedReader(new InputStreamReader(htc.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line ;
            while( (line = br.readLine())!= null){
                sb.append(line + '\n');
            }
            return sb.toString();
        }
        catch(Exception e){
            return null ;
        }


    }
}
