package zakaria.hili.clientgps.Services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import zakaria.hili.clientgps.Entities.JSONEntitie;

/**
 * Created by HILI on 05/11/2015.
 */
public interface HttpServices {

    void sendHttpRequest(JSONEntitie jsonEntitie,String url) throws IOException;

}
