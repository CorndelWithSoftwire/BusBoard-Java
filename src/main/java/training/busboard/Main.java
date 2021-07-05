package training.busboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {
        // Your code here!
        String stopId = getStringFromUser("Enter the stop ID: ");

        try{
            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            String response = client.target("https://api.tfl.gov.uk/StopPoint/"+ stopId +"/Arrivals")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

            ArrayList<Bus> buses = processJson(response);
            for (int i = 0; i < 5; i++){
                System.out.println(buses.get(i).getId() + ": " + 
                                   buses.get(i).getIntegerTime() + "min");
            }
        }
        catch(Exception e){
            System.out.println("Invalid bus ID.");
        }
       
    }

    public static ArrayList<Bus> processJson(String json){
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
            System.out.println("An error has occured.");
        }
        return buses;
    }

    public static String getStringFromUser(String message){
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        sc.close();
        return response;
    }
}	
