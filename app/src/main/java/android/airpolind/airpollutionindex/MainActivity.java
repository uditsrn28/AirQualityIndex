package android.airpolind.airpollutionindex;

import android.airpolind.airpollutionindex.controller.AirIndexContoller;
import android.airpolind.airpollutionindex.entity.AirIndex;
import android.airpolind.airpollutionindex.interfaces.ICallback;
import android.airpolind.airpollutionindex.utils.CityMapping;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner citySelectSpinner;
    private Context mContext;
    private TextView resultTextView;
    private Button checkDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citySelectSpinner = (Spinner) findViewById(R.id.citySelect);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        checkDetails = (Button) findViewById(R.id.checkDetails);
        mContext = getApplicationContext();
        setDataInSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        getAirIndex(CityMapping.getCode((String) parent.getItemAtPosition(position)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    private void setDataInSpinner() {
        List<String> cities = CityMapping.getCities();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cities);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySelectSpinner.setAdapter(spinnerAdapter);
        citySelectSpinner.setOnItemSelectedListener(this);
    }

    private void getAirIndex(int cityCode) {
        AirIndexContoller airIndexContoller = new AirIndexContoller(mContext);
        airIndexContoller.getCreativeTypes(cityCode, getAirIndexCallback());
    }

    private ICallback<String> getAirIndexCallback() {
        return new ICallback<String>() {
            @Override
            public void getResponse(String airIndexResponse) {
                try {
                    Gson gson = new Gson();
                    AirIndex airIndex = gson.fromJson(airIndexResponse, AirIndex.class);
                    setResults(airIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setResults(AirIndex airIndex) {
        String result = "Place : " + airIndex.getTitle() + "\n" +
                "Last Updated : " + airIndex.getDate() + "\n";
        if (airIndex.getAirQualityIndex().getValue() > 0) {
            result = result + airIndex.getAirQualityIndex().getParam() + " : " + String.valueOf(airIndex.getAirQualityIndex().getValue()) + " \n" +
                    "color : " + airIndex.getAirQualityIndex().getColor() + " \n";
            enableButtonForMoreDetails(airIndex);
        } else {
            result = result + "Air Index : Not Available";
            checkDetails.setVisibility(View.GONE);
        }
        resultTextView.setText(result);
    }

    private void enableButtonForMoreDetails(AirIndex airIndex) {
        checkDetails.setVisibility(View.VISIBLE);
        checkDetails.setBackgroundColor(Color.parseColor(airIndex.getAirQualityIndex().getColor()));
        clickListener(airIndex);
    }

    private void clickListener(final AirIndex airIndex) {
        checkDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle sendBundle = new Bundle();
                Gson gson = new Gson();
                sendBundle.putString("airIndexJson", gson.toJson(airIndex));
                Intent i = new Intent(MainActivity.this, Details.class);
                i.putExtras(sendBundle);
                startActivity(i);
            }
        });
    }
}
