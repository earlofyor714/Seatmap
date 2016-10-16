package service;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    public List<String> parseLongitudes(String input) {
        List<String> longitudes = new ArrayList<String>();

        if (input.length()==0) {
            return longitudes;
        }

        String parsee = input;
        //while (parsee.contains("Longitude")) {
            String[] noCommas = input.split("Longitude:");
            String[] noColons = noCommas[1].split(",");
            longitudes.add(noColons[0]);
            //parsee = noColons[1];
        //}
        return longitudes;
    }

    public List<String> parseLatitudes(String input) {
        List<String> latitudes = new ArrayList<String>();

        if (input.length()==0) {
            return latitudes;
        }

        String parsee = input;

        //while (parsee.contains("Latitude")) {
            String[] noCommas = input.split("Latitude:");
            String[] noBrackets = noCommas[1].split("}");
            latitudes.add(noBrackets[0]);
            //parsee = noBrackets[1];
        //}
        return latitudes;
    }
}
