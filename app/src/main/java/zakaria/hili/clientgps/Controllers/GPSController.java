package zakaria.hili.clientgps.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.location.Location;
import android.util.Log;

import zakaria.hili.clientgps.Entities.JSONEntitie;
import zakaria.hili.clientgps.Metier.GPSMetier;
import zakaria.hili.clientgps.Metier.GPSMetierImpl;
import zakaria.hili.clientgps.Services.HttpServices;
import zakaria.hili.clientgps.Services.HttpServicesImpl;
import zakaria.hili.clientgps.Services.HttprequestThread;

import static zakaria.hili.clientgps.Services.HttpServicesImpl.*;

/**
 * Created by HILI on 05/11/2015.
 */
public class GPSController {
    //private Context context;
    private GPSMetierImpl gps = new GPSMetierImpl();
    private HttpServicesImpl services= new HttpServicesImpl();
    private HttprequestThread httprequest = new HttprequestThread();
    public GPSController (){
    }


    public void fakePositionStart(String Id,double lat,double lon, boolean isServer,String url){

        JSONEntitie json= gps.createJSON(Id, lat, lon, isServer);

        try {

            httprequest.setAll(json,url);
            httprequest.demarrer();
            httprequest.start();
        }catch (Exception e){
            Log.d("Error", e.toString());
        }
    }
    public void fakePositionStop()
    {
       httprequest.arret();

    }
    public void fakesetNewInfo(String Id,double lat,double lon){
        JSONEntitie jsonEntitie = new JSONEntitie(Id,lat,lon,false);
        httprequest.setJsonEntitie(jsonEntitie);
    }
    public void fakePosition(String Id,double lat,double lon, boolean isServer,String url){
        if(httprequest.isRunning()){
            fakesetNewInfo(Id,lat,lon);
        }
        else fakePositionStart(Id,lat,lon,isServer,url);
    }


}
