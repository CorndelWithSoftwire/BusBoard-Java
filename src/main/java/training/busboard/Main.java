package training.busboard;

import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {
        // Your code here!
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String response = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jNode = mapper.readTree(response);

            ArrayList<Bus> buses = new ArrayList<Bus>();
            //traverse the jsonnode here and input them into the arraylist
            for (JsonNode root : jNode){
                int timeToStationInS = Integer.parseInt(root.get("timeToStation").asText());
                String busId = root.get("lineId").asText();
                Bus bus = new Bus(timeToStationInS, busId);
                buses.add(bus);
            }

            Collections.sort(buses, new SortbyTime<Bus>());

            for (int i = 0; i < 5; i++){
                System.out.println(buses.get(i).getId() + ": " + buses.get(i).getIntegerTime() + "min");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }
}	
