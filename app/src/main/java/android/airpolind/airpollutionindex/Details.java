package android.airpolind.airpollutionindex;

import android.airpolind.airpollutionindex.entity.AirIndex;
import android.airpolind.airpollutionindex.entity.AirMetrics;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;


public class Details extends Activity {

    private TextView detailsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsTextView = (TextView) findViewById(R.id.details);
        showDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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

    private void showDetails(){
        Bundle receiveBundle = this.getIntent().getExtras();
        final String airIndexJson = receiveBundle.getString("airIndexJson");
        Gson gson = new Gson();
        AirIndex airIndex = gson.fromJson(airIndexJson,AirIndex.class);
        String result = "Place : " + airIndex.getTitle() + "\n" +
                "Last Updated : " + airIndex.getDate() + "\n";
        String details = new String();
        if (airIndex.getAirQualityIndex().getValue() > 0) {
            details = details + airIndex.getAirQualityIndex().getParam() + " : " + String.valueOf(airIndex.getAirQualityIndex().getValue()) + " \n" +
                    "color : " + airIndex.getAirQualityIndex().getColor() + " \n";
            for (AirMetrics airMetrics : airIndex.getAirMetricses()) {
                details = details + "Pollutant Type : " +  airMetrics.getName() + "\n" +
                        "Resuts For " + airMetrics.getAvgDesc() + "\n" +
                        "\tAverage - " + airMetrics.getAvg() + "\n" +
                        "\tMininum - " + airMetrics.getMin() + "\n" +
                        "\tMaximum - " + airMetrics.getMax() + "\n";
            }
        } else {
            details = details + "Air Index : Not Available";
        }
        detailsTextView.setText(result + details);
    }
}
