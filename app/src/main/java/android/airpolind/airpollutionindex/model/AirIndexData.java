package android.airpolind.airpollutionindex.model;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.airpolind.airpollutionindex.backend.http.HTTPGetter;
import android.airpolind.airpollutionindex.backend.http.HTTPPoster;
import android.airpolind.airpollutionindex.entity.AirIndex;
import android.airpolind.airpollutionindex.utils.Constants;
import android.airpolind.airpollutionindex.utils.Utils;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;


public class AirIndexData {

    public String getPostData(int cityCode, Context context) {
        String responseJsonString = new String();
        String url = new String();
        if (Utils.checkInternetConnection(context)) {
            try {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c.getTime());
                url = Constants.BASE_URL.replace(Constants.REPLACE_CHARS_CITY_CODE, String.valueOf(cityCode))
                        .replace(Constants.REPLACE_CHARS_DATE, URLEncoder.encode(formattedDate, "utf-8"));
                responseJsonString = HTTPGetter.getResponse(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d("API", "Internet is not working");
        }
        return responseJsonString;
    }
}
