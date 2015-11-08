package zakaria.hili.clientgps.Services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import zakaria.hili.clientgps.Entities.JSONEntitie;
import zakaria.hili.clientgps.Metier.GPSMetierImpl;


public class HttpServicesImpl  implements HttpServices {

    private GPSMetierImpl gpsMetier= new GPSMetierImpl();





    @Override
    public void sendHttpRequest(JSONEntitie jsonEntitie,String url)  {



            //JSONEntitie jsonEntitie = gpsMetier.createJSON(Id,lat,lon,isServer);
            //if(jsonEntitie==null) Log.d("prob","null");
        try {
            final URL obj = new URL(url);
        final String message= gpsMetier.jsonToString(jsonEntitie);




                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        //add reuqest header
                        con.setRequestMethod("POST");
                        con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                        con.setRequestProperty("Accept","*/*");
                        con.setRequestProperty("Content-Type", "application/json");
                        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                        //con.setRequestProperty("Data", message);
                        String urlParameters = message;

                        // Send post request
                        con.setDoOutput(true);
                        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                        wr.writeBytes(urlParameters);
                        wr.flush();
                        wr.close();

                        int responseCode = con.getResponseCode();


                        //Log.d("Post parameters : ", urlParameters);
                        Log.d("Response Code : ", String.valueOf(responseCode));
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        //print result
                        con.disconnect();
                        Log.d("Response  : ", response.toString());


                    }catch (Exception e){

                        Log.e("error sendHttpRequest", e.toString());
                    }












      /*  while(true){
            String USER_AGENT = "Mozilla/5.0";
            HttpPost postRequest = new HttpPost("https://selfsolve.apple.com/wcResults.do");
            postRequest.setHeader("User-Agent", USER_AGENT);

    HttpClient httpClient = HttpClients.createDefault();

    StringEntity input = new StringEntity(message);
    input.setContentType("application/json");
    postRequest.setEntity(input);

    Log.d("test", message);
    HttpResponse response = httpClient.execute(postRequest);
    Log.d("test2", message);


    BufferedReader br = new BufferedReader(
            new InputStreamReader((response.getEntity().getContent())));
    Log.d("Ping", String.valueOf(response.getStatusLine().getStatusCode()));

    StringBuffer result = new StringBuffer();
    String line = "";
    while ((line = br.readLine()) != null) {
        result.append(line);
    }

    System.out.println(result.toString());
    //System.out.println();
    //GPSMetierImpl gpsMetier =new GPSMetierImpl();
    //gpsMetier.stringToJSON(,response.getStatusLine().getStatusCode());
    br.close();

        /*    try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/

    }
}
