package zakaria.hili.clientgps.Metier;

import org.json.JSONArray;

import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 05/11/2015.
 */
public interface GPSMetier {
    JSONEntitie createJSON(String Id, double lat, double lon, boolean isServer);
    String jsonToString(JSONEntitie jsonEntitie);
    JSONEntitie stringToJSON(String jsonString, int codeResponse);

}
