package training.busboard;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;


public class BusStop{
    private String name;
    private String id;
    private double distance;
    private ArrayList<BusTime> busTimes;

    public BusStop(String name, String id, double distance) {
        this.name = name;
        this.id = id;
        this.distance = distance;
        busTimes = new ArrayList<>();
        addBusTime(id);
    }

    private void addBusTime(String stopId){
        try{
            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            String response = getApiResponse("https://api.tfl.gov.uk/StopPoint/"+ stopId +"/Arrivals", client);

            ArrayList<Bus> buses = JsonParser.extractBusesFromJson(response);
            for (int i = 0; i < 5; i++){
                BusTime time = new BusTime(buses.get(i).getId(), Integer.toString(buses.get(i).getIntegerTime()));
                busTimes.add(time);
            }
        }
        catch(Exception e){
            System.out.println("An error has occured while printing bus times.");
        } 
    }

    public ArrayList<BusTime> getBusTimes() {
        return busTimes;
    }

    public static String getApiResponse(String url, Client client) throws Exception {
        return client.target(url)
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getDistance(){
        return distance;
    }

}