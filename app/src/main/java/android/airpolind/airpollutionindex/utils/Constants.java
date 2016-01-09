/**
 * @author Udit Sarin
 */
package android.airpolind.airpollutionindex.utils;


public class Constants {

	public static final String BASE_URL = new String("http://164.100.160.234:9000/metrics/station/%cityCode%?h=23&d=%date%");
	public static final String REPLACE_CHARS_CITY_CODE = new String("%cityCode%");
	public static final String REPLACE_CHARS_DATE = new String("%date%");
	/**
     * testing variable
     */
    public static final Boolean LOG_ENABLED = Boolean.TRUE;
    public static final String TAG = "Tyroo";
}
