package android.airpolind.airpollutionindex.utils;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Udit on 1/8/16.
 */
public class CityMapping {
    static HashMap<String, Integer> cityCodes = new HashMap<String, Integer>();

    static {
        init();
    }



    public static void init() {
        cityCodes.put("Delhi College Of Engineering",94);
        cityCodes.put("Income Tax Office",795);
        cityCodes.put("Shadipur",796);
        cityCodes.put("IHBAS",797);
        cityCodes.put("NSIT Dwarka",798);
        cityCodes.put("Sector16A Faridabad",799);
        cityCodes.put("Lalbagh, West Lucknow",801);
        cityCodes.put("Central School",801);
        cityCodes.put("Sanjay Palace",803);
        cityCodes.put("Nehru Nagar",804);
        cityCodes.put("Ardhali Bazar",805);
        cityCodes.put("Maninagar",806);
        cityCodes.put("Sanathnagar",807);
        cityCodes.put("BTM Layout",808);
        cityCodes.put("Peenya",809);
        cityCodes.put("BWSSB Kadabesanahalli",810);
        cityCodes.put("Alandur Bus Depot",811);
        cityCodes.put("IIT",812);
        cityCodes.put("Manali",813);
        cityCodes.put("Visakhapatnam",825);
        cityCodes.put("Vijayawada",826);
        cityCodes.put("Patna",827);
        cityCodes.put("Raipur",828);
        cityCodes.put("Rajkot",829);
        cityCodes.put("Surat",830);
        cityCodes.put("Vadodara",831);
        cityCodes.put("Srinagar",832);
        cityCodes.put("Dhanbad",833);
        cityCodes.put("Ranchi",834);
        cityCodes.put("Bhopal",835);
        cityCodes.put("Gwalior",836);
        cityCodes.put("Indore",837);
        cityCodes.put("Jabalpur",838);
        cityCodes.put("Aurangabad",839);
        cityCodes.put("Mumbai",840);
        cityCodes.put("Nagpur",841);
        cityCodes.put("Nashik",842);
        cityCodes.put("Pune",843);
        cityCodes.put("Vasai-Virar",844);
        cityCodes.put("Thane",845);
        cityCodes.put("Navi Mumbai",846);
        cityCodes.put("Kalyan Dombivali",847);
        cityCodes.put("Pimpri-Chinchwad",848);
        cityCodes.put("Amritsar",849);
        cityCodes.put("Ludhiana",850);
        cityCodes.put("Jaipur",851);
        cityCodes.put("Jodhpur",852);
        cityCodes.put("Kota",853);
        cityCodes.put("Coimbatore",854);
        cityCodes.put("Madurai",855);
        cityCodes.put("Allahabad",857);
        cityCodes.put("Ghaziabad",858);
        cityCodes.put("Meerut",859);
        cityCodes.put("Kolkata",860);
        cityCodes.put("Howrah",861);
        cityCodes.put("Bhubaneswar",862);
        cityCodes.put("Gandhinagar",863);
        cityCodes.put("Shimla",864);
        cityCodes.put("Thiruvananthapuram",865);
        cityCodes.put("Dehradun",866);
        cityCodes.put("Panaji",867);
        cityCodes.put("Guwahati",868);
        cityCodes.put("Shillong",869);
        cityCodes.put("Imphal",870);
        cityCodes.put("Itanagar",871);
        cityCodes.put("Aizawl",872);
        cityCodes.put("Kohima",873);
        cityCodes.put("Gangtok",874);
        cityCodes.put("Agartala",875);
        cityCodes.put("Chandigarh",876);
        cityCodes.put("Puducherry",877);
        cityCodes.put("Port Blair",878);
        cityCodes.put("Silvassa",879);
        cityCodes.put("Daman",880);
        cityCodes.put("Kavaratti",881);
        cityCodes.put("Civil Lines - DPCC",889);
        cityCodes.put("IGI Airport - DPCC",890);
        cityCodes.put("Mandir Marg - DPCC",891);
        cityCodes.put("Anand Vihar - DPCC",892);
        cityCodes.put("R. K. Puram - DPCC",893);
        cityCodes.put("Punjabi Bagh - DPCC",894);
        cityCodes.put("Airoli - NMMC",895);
        cityCodes.put("Talkatora District Industries Center",896);
        cityCodes.put("Karve Road Pune",907);
        cityCodes.put("Chandrapur",908);
        cityCodes.put("Bandra - MPCB",909);
        cityCodes.put("Zoo Park, Bahadurpura West",910);
        cityCodes.put("IGSC Planetarium Complex - BSPCB",911);
        cityCodes.put("Muzaffarpur Collectorate - BSPCB",912);
        cityCodes.put("VK Industrial Area Jaipur - RSPCB",913);
        cityCodes.put("Collectorate Jodhpur - RSPCB",914);
        cityCodes.put("Sector 6 Panchkula - HSPCB",915);
        cityCodes.put("City Railway Station - KSPCB",916);
        cityCodes.put("SaneguravaHalli - KSPCB",917);
        cityCodes.put("Vikas Sadan Gurgaon - HSPCB",918);
        cityCodes.put("Collectorate Gaya - BSPCB",919);
        cityCodes.put("Haldia - WBSPCB",920);
    }

    public static Integer getCode(String city) {
        return cityCodes.get(city);
    }

    public static List<String> getCities(){
        List<String> cities = new ArrayList<String>();
        for (String key: cityCodes.keySet()) {
            cities.add(key);
        }
        Collections.sort(cities);
        return cities;
    }
}
