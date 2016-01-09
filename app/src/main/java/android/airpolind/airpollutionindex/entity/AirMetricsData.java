package android.airpolind.airpollutionindex.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Udit on 1/8/16.
 */
public class AirMetricsData {

    @SerializedName("date")
    public String date;

    @SerializedName("value")
    public int value;

    @SerializedName("tooltip")
    public String toolTip;

    @SerializedName("color")
    public String color;

    public AirMetricsData(String date, int value, String toolTip, String color) {
        this.date = date;
        this.value = value;
        this.toolTip = toolTip;
        this.color = color;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public String getToolTip() {
        return toolTip;
    }

    public String getColor() {
        return color;
    }
}
