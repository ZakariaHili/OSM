package zakaria.hili.clientgps.Dao;

import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 05/11/2015.
 */
public interface DaoApp {
    JSONEntitie createJSON(String Id,double lat, double lon,boolean isServer);
    String jsonToString(JSONEntitie jsonEntitie);
    JSONEntitie stringToJSON(String jsonString, int codeResponse);

}
