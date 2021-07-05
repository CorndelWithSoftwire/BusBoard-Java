package training.busboard;

import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String postCode = getStringFromUser("Enter the postcode:" );
        try{
            String postcodeData = getApiResponse("http://api.postcodes.io/postcodes/"+ postCode, client);
            Coordinate coordinate = JsonParser.extractCoordinatesFromJson(postcodeData);
            String responseForBs = getApiResponse("https://api.tfl.gov.uk/StopPoint//?lat=" + coordinate.getlatitude() +
                                                "&lon=" + coordinate.getlongitude() +
                                                "&stopTypes=NaptanPublicBusCoachTram", client);

            // input the coordinate we calculated from the postcode into the 2nd API to get the near stops
            ArrayList<BusStop> stops = JsonParser.extractBusStopsFromJson(responseForBs);
            for(int i= 0; i < 2; i++ )
            {
                System.out.println(stops.get(i).getName() + ": " + (int)stops.get(i).getDistance() + "km");
                printBusTimes(stops.get(i).getId());
            }
        }
        catch(Exception e){
            System.out.println("Invalid Post Code.");
        }
    }

    public static void printBusTimes(String stopId){
        try{
            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            String response = getApiResponse("https://api.tfl.gov.uk/StopPoint/"+ stopId +"/Arrivals", client);

            ArrayList<Bus> buses = JsonParser.extractBusesFromJson(response);
            for (int i = 0; i < 5; i++){
                System.out.println(buses.get(i).getId() + ": " + buses.get(i).getIntegerTime() + "min");
            }
        }
        catch(Exception e){
            System.out.println("An error has occured while printing bus times.");
        } 
    }

    public static String getApiResponse(String url, Client client) throws Exception {
        return client.target(url)
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
    }

    public static String getStringFromUser(String message){
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        sc.close();
        return response;
    }
}	
