package training.busboard;

import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public static ArrayList<BusStop> extractBusStopsFromJson(String json){
        ArrayList<BusStop> stops = new ArrayList<>();
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jNode = mapper.readTree(json);
            for (JsonNode stop : jNode.get("stopPoints")){
                BusStop busStop = new BusStop(stop.get("commonName").asText(), 
                                                stop.get("id").asText(), 
                                                stop.get("distance").asDouble());
                stops.add(busStop);
            }
            Collections.sort(stops, new SortbyDistance<BusStop>());
        }
        catch (Exception e){
            System.out.println("An error has occured while getting bus stops.");
        }

        return stops;
    }

    public static ArrayList<Bus> extractBusesFromJson(String json){
        ArrayList<Bus> buses = new ArrayList<Bus>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jNode = mapper.readTree(json);
    
            //traverse the jsonnode here and input them into the arraylist
            for (JsonNode root : jNode){
                int timeToStationInS = Integer.parseInt(root.get("timeToStation").asText());
                String busId = root.get("lineId").asText();
                Bus bus = new Bus(timeToStationInS, busId);
                buses.add(bus);
            }
    
            Collections.sort(buses, new SortbyTime<Bus>());
        }
        catch (Exception e){
            System.out.println("An error has occured while getting buses.");
        }
        return buses;
    }

    public static Coordinate extractCoordinatesFromJson(String json){
        Coordinate coordinate = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jNode = mapper.readTree(json);
            String longitude = jNode.get("result").get("longitude").asText();
            String latitude = jNode.get("result").get("latitude").asText();
            coordinate = new Coordinate(longitude, latitude);
        }
        catch (Exception e){
            System.out.println("An error has occured while finding postcode coordinates.");
        }
        return coordinate;

    }


    
}
