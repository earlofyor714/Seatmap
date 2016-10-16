package service;

import org.json.JSONArray;
import org.json.JSONObject;
import proxy.LocationProxy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class api {
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@QueryParam("l1") String l1) throws Exception {
        LocationProxy locationProxy = new LocationProxy();

        String lLat = "1";
        String lLong = "2";
        String uLat = "0";
        String uLong = "5";

        JSONObject jsonObject = new JSONObject("{\"lLat\":\""+ lLat +
                "\",\"lLong\":\"" + lLong + "\",\"uLat\":\"" + uLat +
                "\",\"uLong\":\"" + uLong + "\"}");

        System.out.println("response: " + jsonObject.toString());

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toString()).build();
    }

    @POST
    @Path("/triangulate")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response triangulate(String test) throws Exception {
        System.out.println("request: " + test);

        //String lLat = "0";
        //String lLong = "0";
        //String uLat = test;
        //String uLong = "8"; //bottom left, top right

        //need to parse the string

        StringParser stringParser = new StringParser();

        //request: {Longitude:-117.6650439,Latitude:33.881502}
        //2 request: [{Longitude:-117.6650439,Latitude:33.881502},{Longitude:-12.764439,Latitude:90.8843522}]
        List<String> longitudes = stringParser.parseLongitudes(test.trim());
        List<String> latitudes = stringParser.parseLatitudes(test.trim());

        System.out.println("longitude: " + longitudes.size());
        System.out.println("latitude: " + latitudes.size());

        /*String uLat = latitudes.get(0);
        String uLong = longitudes.get(0);
        if (longitudes.size() > 1) {
            lLat = latitudes.get(1);
            lLong = longitudes.get(1);
        } else {
            lLat = "0";
            lLong = "0";
        }*/

        float totalLongitudes = 0;
        float totalLatitudes = 0;
        float radius = (float) 0.1;  //need a better way to determine radius value

        //calculate midpoint
        for (int i=0; i<longitudes.size(); i++) {
            float temp = Float.parseFloat(longitudes.get(i));
            totalLongitudes = totalLongitudes + temp;
        }
        float avgLongitude = totalLongitudes / longitudes.size();

        for (int i=0; i<latitudes.size(); i++) {
            totalLatitudes += Float.parseFloat(latitudes.get(i));
        }
        float avgLatitude = totalLatitudes / latitudes.size();

        //calculate corners of square
        float radiusModifier = radius / Float.parseFloat(String.valueOf(Math.sqrt(2)));

        String lLat = String.valueOf(avgLatitude - radiusModifier);
        String lLong = String.valueOf(avgLongitude - radiusModifier);
        String uLat = String.valueOf(avgLatitude + radiusModifier);
        String uLong = String.valueOf(avgLongitude + radiusModifier);

        //

        JSONObject jsonObject = new JSONObject("{\"lLat\":\""+ lLat +
                "\",\"lLong\":\"" + lLong + "\",\"uLat\":\"" + uLat +
                "\",\"uLong\":\"" + uLong + "\"}");

        System.out.println("post response: " + jsonObject.toString());

        //return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toString()).build();
        return Response.ok(jsonObject.toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}
