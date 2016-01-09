package android.airpolind.airpollutionindex.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Udit on 1/8/16.
 */
public class AirQualityIndex {
    @SerializedName("param")
    public String param;

    @SerializedName("value")
    public int value;

    @SerializedName("remark")
    public String remark;

    @SerializedName("color")
    public String color;

    public AirQualityIndex() {
        this.param = new String();
        this.value = 0;
        this.remark = new String();
        this.color = new String();
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParam() {
        return param;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public String getColor() {
        return color;
    }
}
