package com.example.skynestapplication;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//--------------------------------------------------------------------------------------------------
//Reference: The Code Integrity
//URL: https://www.youtube.com/watch?v=_bsq2FyZx6U
//Use: Draw route between two locations in Android Studio - Google Maps
public class DataParser {

    /**
     * Recieves a JSONObject and returns a list of lists containing latitude and longitude
     */
    public List<List<HashMap<String, String>>> parse(JSONObject jObject) {
        List<List<HashMap<String, String>>> routes = new ArrayList<>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        try {
            jRoutes = jObject.getJSONArray("routes");

            /** Transvering all routes */
            for (int x = 0; x < jRoutes.length(); x++) {
                jLegs = ((JSONObject) jRoutes.get(x)).getJSONArray("legs");
                List path = new ArrayList<HashMap<String, String>>();

                /** Transvering all legs */
                for (int y = 0; y < jLegs.length(); y++) {
                    jSteps = ((JSONObject) jLegs.get(y)).getJSONArray("steps");

                    /** Transvering all steps */
                    for (int z = 0; z < jSteps.length(); z++) {
                        String polyline = "";
                        polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(z)).get("polyline")).get("points");
                        List list = decodePoly(polyline);

                        /** Transvering all points */
                        for (int a = 0; a < list.size(); a++) {
                            HashMap<String, String> hm = new HashMap<>();
                            hm.put("lat", Double.toString(((LatLng) list.get(a)).latitude));
                            hm.put("lng", Double.toString(((LatLng) list.get(a)).longitude));
                            path.add(hm);
                        }

                    }
                    routes.add(path);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }

    //--------------------------------------------------------------------------------------------------
    //Reference: GitHUb Gist - ParryPatel021
    //URL: https://gist.github.com/ParryPatel021/60fe1266135e7f30e6438515619190c2
    //Use: Method to decode polyline points in Java
    private List decodePoly(String encoded) {

        List poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;

    }

}
//--------------------------------------------------------------------------------------------------