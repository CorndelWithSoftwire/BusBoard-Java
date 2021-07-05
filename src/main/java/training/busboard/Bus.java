package training.busboard;

public class Bus{
    private int timeToStation;
    private String id;

    public double getTimeToStation() {
        return timeToStation;
    }

    public String getId() {
        return id;
    }

    public int getIntegerTime(){
        return (int)Math.round(timeToStation);
    }

    public Bus(int timeToStation, String id) {
        this.timeToStation = timeToStation / 60;
        this.id = id;
    }

}