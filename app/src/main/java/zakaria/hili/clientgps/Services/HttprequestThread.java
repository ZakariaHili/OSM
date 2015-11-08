package zakaria.hili.clientgps.Services;

import android.util.Log;

import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 07/11/2015.
 */
public class HttprequestThread extends Thread {



        protected volatile boolean running = false;
        protected HttpServicesImpl httpServices = new HttpServicesImpl();

        protected  JSONEntitie jsonEntitie;
        protected String url;

        public void setAll(JSONEntitie jsonEntitie,String url){
            this.jsonEntitie =jsonEntitie;
            this.url=url;
        }
        public void setJsonEntitie(JSONEntitie jsonEntitie){
            this.jsonEntitie=jsonEntitie;
        }
        public HttprequestThread(JSONEntitie jsonEntitie, String url){
            this.jsonEntitie = jsonEntitie;
            this.url=url;
        }
        public boolean isRunning ()
        {
            return running;
        }
        public HttprequestThread(){

        }

        public void arret() {
            running = false;
        }
        public void demarrer() {
        running = true;
    }
        public void run() {
            try {
                 while (running) {
                   httpServices.sendHttpRequest(jsonEntitie, this.url);
                     this.sleep(10000);
                }
            }catch (Exception e) {
                Log.e("error HttprequestThread", e.toString());
            }
        }


}
