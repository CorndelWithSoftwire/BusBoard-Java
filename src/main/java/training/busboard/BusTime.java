package training.busboard;

public class BusTime {

    private String busId;
    private String time;

    public BusTime(String busId, String time){
        this.busId = busId;
        this.time = time;
    }

    public String getBusId() {
        return busId;
    }

    public String getTime() {
        return time;
    }
    
}
