package zakaria.hili.clientgps.Entities;

import org.json.JSONObject;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.LinkedList;

/**
 * Created by HILI on 05/11/2015.
 */
public class JSONEntitie {
    private String Id;
    private double lat;
    private double lon;
    private boolean isServer;
    private LinkedList<String> servers;
    

    public JSONEntitie() {
    }

    public JSONEntitie(String jsonString, int codeResponse) throws JSONException {
        JSONObject  jsonRootObject = new JSONObject(jsonString);

        if(codeResponse==303) {
            JSONArray jsonArray = jsonRootObject.optJSONArray("servers");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                servers.add(jsonObject.optString("IP").toString());
            }

        }
        else if(codeResponse==200){
            String IsServer=jsonRootObject.get("servers").toString();
            if(IsServer.compareTo("true")==0) isServer=true;
            else isServer=false;
        }



    }

    public JSONEntitie(String id, double lat, double lon, boolean isServer) {

        this.Id = id;
        this.lat = lat;
        this.lon = lon;
        this.isServer = isServer;


    }

    public void setId(String id) {
        Id = id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public void setServers(LinkedList<String> servers) {
        this.servers = servers;
    }
    public void addServer(String server){
        this.servers.add(server);
    }
    public String getId() {
        return Id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public boolean isServer() {
        return isServer;
    }

    public LinkedList<String> getServers() {
        return servers;
    }
}
