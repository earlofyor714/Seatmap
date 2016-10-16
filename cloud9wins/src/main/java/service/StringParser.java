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
        String[] noCommas = parsee.split("Longitude:");
        for (int i=1; i<noCommas.length; i++) {
            String[] noColons = noCommas[i].split(",");
            longitudes.add(noColons[0]);
        }
        return longitudes;
    }

    public List<String> parseLatitudes(String input) {
        List<String> latitudes = new ArrayList<String>();

        if (input.length()==0) {
            return latitudes;
        }

        String parsee = input;

        String[] noCommas = parsee.split("Latitude:");
        for (int i=1; i<noCommas.length; i++) {
            String[] noBrackets = noCommas[i].split("}");
            latitudes.add(noBrackets[0]);
        }
        return latitudes;
    }
}
