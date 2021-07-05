package training.busboard;

public class BusStop{
    private String name;
    private String id;
    private double distance;

    public BusStop(String name, String id, double distance) {
        this.name = name;
        this.id = id;
        this.distance = distance;
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