package android.airpolind.airpollutionindex.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Udit on 1/8/16.
 */
public class AirMetrics {

    @SerializedName("name")
    public String name;

    @SerializedName("avg")
    public int avg;

    @SerializedName("avgDesc")
    public String avgDesc;

    @SerializedName("min")
    public int min;

    @SerializedName("max")
    public int max;

    @SerializedName("data")
    public ArrayList<AirMetricsData> airMetricData;

    public AirMetrics() {
        this.name = new String();
        this.avg = 0;
        this.avgDesc = new String();
        this.min = 0;
        this.max = 0;
        this.airMetricData = new ArrayList<AirMetricsData>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public void setAvgDesc(String avgDesc) {
        this.avgDesc = avgDesc;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAirMetricData(ArrayList<AirMetricsData> airMetricData) {
        this.airMetricData = airMetricData;
    }

    public String getName() {
        return name;
    }

    public int getAvg() {
        return avg;
    }

    public String getAvgDesc() {
        return avgDesc;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public ArrayList<AirMetricsData> getAirMetricData() {
        return airMetricData;
    }
}
