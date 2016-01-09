package android.airpolind.airpollutionindex.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Udit on 1/8/16.
 */
public class AirIndex {
    @SerializedName("title")
    public String title;

    @SerializedName("down")
    public boolean down;

    @SerializedName("downmessage")
    public String downMessage;

    @SerializedName("date")
    public String date;

    @SerializedName("aqi")
    public AirQualityIndex airQualityIndex;

    @SerializedName("metrics")
    public List<AirMetrics> airMetricses;

    public AirIndex() {
        this.title = new String();
        this.down = false;
        this.downMessage = new String();
        this.date = new String();
        this.airQualityIndex = new AirQualityIndex();
        this.airMetricses = new ArrayList<AirMetrics>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setDownMessage(String downMessage) {
        this.downMessage = downMessage;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAirQualityIndex(AirQualityIndex airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public void setAirMetricses(ArrayList<AirMetrics> airMetricses) {
        this.airMetricses = airMetricses;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDown() {
        return down;
    }

    public String getDownMessage() {
        return downMessage;
    }

    public String getDate() {
        return date;
    }

    public AirQualityIndex getAirQualityIndex() {
        return airQualityIndex;
    }

    public List<AirMetrics> getAirMetricses() {
        return airMetricses;
    }
}
