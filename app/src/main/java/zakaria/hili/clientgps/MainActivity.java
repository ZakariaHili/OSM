package zakaria.hili.clientgps;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zakaria.hili.clientgps.Controllers.GPSController;
import zakaria.hili.clientgps.Entities.JSONEntitie;
import zakaria.hili.clientgps.Services.HttpServices;
import zakaria.hili.clientgps.Services.HttpServicesImpl;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyApp", "I am here");

        final GPSController request = new GPSController();
        setContentView(R.layout.activity_main);
        Button sendGps = (Button) findViewById(R.id.button);
        final EditText id =(EditText) findViewById(R.id.idsimulation);
        final EditText latString = (EditText) findViewById(R.id.latsimulation);
        final EditText lonString =(EditText) findViewById(R.id.lonsimulation);
        sendGps.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {
                try {

                    WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    WifiInfo info = manager.getConnectionInfo();
                    Double lat,lon;
                    lat = Double.parseDouble(String.valueOf(latString.getText()));
                    lon = Double.parseDouble(String.valueOf(lonString.getText()));
                    if(id.getText().toString().length()==0) {
                        request.fakePosition(info.getMacAddress()
                                , lat, lon, false, "http://httpbin.org/post");
                    }
                    else
                        request.fakePosition(id.getText().toString()
                                , lat, lon, false, "http://httpbin.org/post");
                }catch(Exception e){}
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
