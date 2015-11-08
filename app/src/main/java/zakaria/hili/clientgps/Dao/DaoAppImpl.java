package zakaria.hili.clientgps.Dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 05/11/2015.
 */
public class DaoAppImpl implements DaoApp {
    @Override
    public JSONEntitie createJSON(String Id, double lat, double lon, boolean isServer) {

        JSONEntitie jsonEntitie= new JSONEntitie(Id,lat, lon,isServer);
        return jsonEntitie;
    }

    @Override
    public String jsonToString(JSONEntitie jsonEntitie) {
        try {
            JSONObject position = new JSONObject();
            position.put("lat", jsonEntitie.getLat());
            position.put("lon", jsonEntitie.getLon());
            JSONObject client = new JSONObject();
            client.put("ID", jsonEntitie.getId());
            client.put("Position", position);
            JSONObject json = new JSONObject();
            json.put("client", client);
            json.put("isServer", jsonEntitie.isServer());
            return json.toString();
        }catch (Exception e){
            Log.e("Ouups : createJSON", e.toString());
            return null;
        }

        }

    @Override
    public JSONEntitie stringToJSON(String jsonString, int codeResponse) {
    try {
       JSONObject jsonRootObject = new JSONObject(jsonString);
        JSONEntitie jsonEntitie=new JSONEntitie();
         if (codeResponse == 303) {
            JSONArray jsonArray = jsonRootObject.optJSONArray("servers");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonEntitie.addServer(jsonObject.optString("IP").toString());
         }

        } else if (codeResponse == 200) {
            String IsServer = jsonRootObject.get("servers").toString();
            if (IsServer.compareTo("true") == 0) jsonEntitie.setIsServer(true);
            else
                jsonEntitie.setIsServer(false);
    }
}
catch (Exception e){
    System.out.println("Help stringToJSON :'( ");
}
        return null;
    }
}
