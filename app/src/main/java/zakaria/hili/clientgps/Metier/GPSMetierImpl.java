package zakaria.hili.clientgps.Metier;

import android.util.Log;

import zakaria.hili.clientgps.Dao.DaoApp;
import zakaria.hili.clientgps.Dao.DaoAppImpl;
import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 05/11/2015.
 */
public class GPSMetierImpl implements  GPSMetier {

    private DaoAppImpl dao=new DaoAppImpl();

    @Override
    public String jsonToString(JSONEntitie jsonEntitie) {
        return this.dao.jsonToString(jsonEntitie);
    }

    @Override
    public JSONEntitie createJSON(String Id, double lat, double lon, boolean isServer) {

        return this.dao.createJSON(Id,lat,lon,isServer);
    }



    @Override
    public JSONEntitie stringToJSON(String jsonString, int codeResponse) {
        return this.dao.stringToJSON(jsonString,codeResponse);
    }
}
